class DummyClass_35721 {
  @Test
  public void testResilientLogging() throws Exception {
    Configuration hConf = new Configuration();
    CConfiguration cConf = CConfiguration.create();

    File datasetDir = new File(tmpFolder.newFolder(), "datasetUser");
    //noinspection ResultOfMethodCallIgnored
    datasetDir.mkdirs();

    cConf.set(Constants.Dataset.Manager.OUTPUT_DIR, datasetDir.getAbsolutePath());
    cConf.set(Constants.Service.MASTER_SERVICES_BIND_ADDRESS, "localhost");

    cConf.set(Constants.Dataset.Executor.ADDRESS, "localhost");
    cConf.setInt(Constants.Dataset.Executor.PORT, Networks.getRandomPort());

    cConf.set(Constants.CFG_LOCAL_DATA_DIR, tmpFolder.newFolder().getAbsolutePath());

    Injector injector = Guice.createInjector(
      new ConfigModule(cConf, hConf),
      new IOModule(),
      new ZKClientModule(),
      new KafkaClientModule(),
      new InMemoryDiscoveryModule(),
      new NonCustomLocationUnitTestModule(),
      new DataFabricModules().getInMemoryModules(),
      new DataSetsModules().getStandaloneModules(),
      new DataSetServiceModules().getInMemoryModules(),
      new TransactionMetricsModule(),
      new ExploreClientModule(),
      new LocalLogAppenderModule(),
      new NamespaceAdminTestModule(),
      new AuthorizationTestModule(),
      new AuthorizationEnforcementModule().getInMemoryModules(),
      new AuthenticationContextModules().getMasterModule(),
      new AbstractModule() {
        @Override
        protected void configure() {
          bind(UGIProvider.class).to(UnsupportedUGIProvider.class);
          bind(OwnerAdmin.class).to(NoOpOwnerAdmin.class);
          bind(MetadataServiceClient.class).to(NoOpMetadataServiceClient.class);
        }
      });

    TransactionManager txManager = injector.getInstance(TransactionManager.class);
    txManager.startAndWait();
    StructuredTableRegistry structuredTableRegistry = injector.getInstance(StructuredTableRegistry.class);
    structuredTableRegistry.initialize();
    StoreDefinition.createAllTables(injector.getInstance(StructuredTableAdmin.class), structuredTableRegistry);

    DatasetOpExecutorService opExecutorService = injector.getInstance(DatasetOpExecutorService.class);
    opExecutorService.startAndWait();

    // Start the logging before starting the service.
    LoggingContextAccessor.setLoggingContext(new WorkerLoggingContext("TRL_ACCT_1", "APP_1", "WORKER_1",
                                                                      "RUN", "INSTANCE"));
    String logBaseDir = "trl-log/log_files_" + new Random(System.currentTimeMillis()).nextLong();

    cConf.set(LoggingConfiguration.LOG_BASE_DIR, logBaseDir);
    cConf.setInt(LoggingConfiguration.LOG_MAX_FILE_SIZE_BYTES, 20 * 1024);
    final LogAppender appender = injector.getInstance(LocalLogAppender.class);
    new LogAppenderInitializer(appender).initialize("TestResilientLogging");

    int failureMsgCount = 3;
    final CountDownLatch failureLatch = new CountDownLatch(failureMsgCount);
    LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
    loggerContext.getStatusManager().add(new StatusListener() {
      @Override
      public void addStatusEvent(Status status) {
        if (status.getLevel() != Status.ERROR || status.getOrigin() != appender) {
          return;
        }
        Throwable cause = status.getThrowable();
        if (cause != null) {
          Throwable rootCause = Throwables.getRootCause(cause);
          if (rootCause instanceof ServiceUnavailableException) {
            String serviceName = ((ServiceUnavailableException) rootCause).getServiceName();
            if (Constants.Service.DATASET_MANAGER.equals(serviceName)) {
              failureLatch.countDown();
            }
          }
        }
      }

}