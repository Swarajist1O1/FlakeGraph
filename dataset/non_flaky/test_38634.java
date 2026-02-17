class DummyClass_38634 {
    @Test(enabled = false)
    public void testPolling() throws Exception {

        // let first event fire
        Thread.sleep(2000L);

        final List<MaterializedConfiguration> events = Lists.newArrayList();

        Object eventHandler = new Object() {
            @Subscribe
            public synchronized void handleConfigurationEvent(MaterializedConfiguration event) {
                events.add(event);
            }
        };
        eventBus.register(eventHandler);
        configFile.setLastModified(System.currentTimeMillis());

        // now wait for second event to fire
        Thread.sleep(2000L);

        Assert.assertEquals(events.size(), 1, String.valueOf(events));

        MaterializedConfiguration materializedConfiguration = events.remove(0);

        Assert.assertEquals(materializedConfiguration.getSourceRunners().size(),1);
        Assert.assertEquals(materializedConfiguration.getSinkRunners().size(), 1);
        Assert.assertEquals(materializedConfiguration.getChannels().size(), 1);


    }

}