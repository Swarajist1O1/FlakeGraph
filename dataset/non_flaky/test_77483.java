class DummyClass_77483 {
    @TestLogging(value = "org.opensearch.monitor.fs:WARN", reason = "to ensure that we log on hung IO at WARN level")
    public void testLoggingOnHungIO() throws Exception {
        long slowLogThreshold = randomLongBetween(100, 200);
        final Settings settings = Settings.builder().put(FsHealthService.SLOW_PATH_LOGGING_THRESHOLD_SETTING.getKey(),
            slowLogThreshold + "ms").build();
        FileSystem fileSystem = PathUtils.getDefaultFileSystem();
        TestThreadPool testThreadPool = new TestThreadPool(getClass().getName(), settings);
        FileSystemFsyncHungProvider disruptFileSystemProvider = new FileSystemFsyncHungProvider(fileSystem,
            randomLongBetween(slowLogThreshold + 1 , 400), testThreadPool);
        fileSystem = disruptFileSystemProvider.getFileSystem(null);
        PathUtilsForTesting.installMock(fileSystem);
        final ClusterSettings clusterSettings = new ClusterSettings(Settings.EMPTY, ClusterSettings.BUILT_IN_CLUSTER_SETTINGS);

        MockLogAppender mockAppender = new MockLogAppender();
        mockAppender.start();

        Logger logger = LogManager.getLogger(FsHealthService.class);
        Loggers.addAppender(logger, mockAppender);
        try (NodeEnvironment env = newNodeEnvironment()) {
            FsHealthService fsHealthService = new FsHealthService(settings, clusterSettings, testThreadPool, env);
            int counter = 0;
            for(Path path : env.nodeDataPaths()){
                mockAppender.addExpectation(
                    new MockLogAppender.SeenEventExpectation(
                        "test" + ++counter,
                        FsHealthService.class.getCanonicalName(),
                        Level.WARN,
                        "health check of [" + path + "] took [*ms] which is above the warn threshold*"));
            }

            //disrupt file system
            disruptFileSystemProvider.injectIODelay.set(true);
            fsHealthService.new FsHealthMonitor().run();
            assertEquals(env.nodeDataPaths().length, disruptFileSystemProvider.getInjectedPathCount());
            assertBusy(mockAppender::assertAllExpectationsMatched);
        } finally {
            Loggers.removeAppender(logger, mockAppender);
            mockAppender.stop();
            PathUtilsForTesting.teardown();
            ThreadPool.terminate(testThreadPool, 500, TimeUnit.MILLISECONDS);
        }
    }

}