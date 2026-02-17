class DummyClass_38638 {
    @Test(timeOut = 10000L)
    public void testFLUME2786() throws Exception {
        final String agentName = "test";
        final int interval = 1;
        final long intervalMs = 1000L;

        File configFile = new File(baseDir, "flume-conf.properties");
        Files.copy(new File(getClass().getClassLoader()
                .getResource("flume-conf.properties.2786").getFile()), configFile);
        File mockConfigFile = spy(configFile);
        when(mockConfigFile.lastModified()).then(new Answer<Long>() {
            @Override
            public Long answer(InvocationOnMock invocation) throws Throwable {
                Thread.sleep(intervalMs);
                return System.currentTimeMillis();
            }

}