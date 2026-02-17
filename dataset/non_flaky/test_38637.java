class DummyClass_38637 {
    @Test
    public void testFLUME1854() throws Exception {
        File configFile = new File(baseDir, "flume-conf.properties");
        Files.copy(new File(getClass().getClassLoader()
                .getResource("flume-conf.properties").getFile()), configFile);
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            EventBus eventBus = new EventBus("test-event-bus");
            PollingPropertiesFileConfigurationProvider configurationProvider =
                    new PollingPropertiesFileConfigurationProvider("host1",
                            configFile, eventBus, 1);
            List<LifecycleAware> components = Lists.newArrayList();
            components.add(configurationProvider);
            Application application = new Application(components);
            eventBus.register(application);
            application.start();
            Thread.sleep(random.nextInt(10000));
            application.stop();
        }
    }

}