class DummyClass_84621 {
    @Test
    public void testValidArguments() {
        String[] args = {"2181", "/data/dir", "60000", "10000"};
        serverConfig.parse(args);

        assertEquals(2181, serverConfig.getClientPortAddress().getPort());
        assertTrue(checkEquality("/data/dir", serverConfig.getDataDir()));
        assertEquals(60000, serverConfig.getTickTime());
        assertEquals(10000, serverConfig.getMaxClientCnxns());
    }

}