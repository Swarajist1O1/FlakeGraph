class DummyClass_38636 {
    @Test
    public void testBasicConfiguration() throws Exception {

        EventBus eventBus = new EventBus("test-event-bus");

        MaterializedConfiguration materializedConfiguration = new
                SimpleMaterializedConfiguration();

        SourceRunner sourceRunner = mockLifeCycle(SourceRunner.class);
        materializedConfiguration.addSourceRunner("test", sourceRunner);

        SinkRunner sinkRunner = mockLifeCycle(SinkRunner.class);
        materializedConfiguration.addSinkRunner("test", sinkRunner);

        Channel channel = mockLifeCycle(Channel.class);
        materializedConfiguration.addChannel("test", channel);


        ConfigurationProvider configurationProvider = mock(ConfigurationProvider.class);
        when(configurationProvider.getConfiguration()).thenReturn(materializedConfiguration);

        Application application = new Application();
        eventBus.register(application);
        eventBus.post(materializedConfiguration);
        application.start();

        Thread.sleep(1000L);

        verify(sourceRunner).start();
        verify(sinkRunner).start();
        verify(channel).start();

        application.stop();

        Thread.sleep(1000L);

        verify(sourceRunner).stop();
        verify(sinkRunner).stop();
        verify(channel).stop();
    }

}