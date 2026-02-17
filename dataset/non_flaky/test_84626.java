class DummyClass_84626 {
    @Test
    public void testWhenInvalidJuteMaxBufferIsConfiguredIOExceptionIsThrown() {
        ZKClientConfig clientConfig = new ZKClientConfig();
        String value = "SomeInvalidInt";
        clientConfig.setProperty(ZKConfig.JUTE_MAXBUFFER, value);
        // verify ClientCnxnSocketNIO creation
        try {
            new ClientCnxnSocketNIO(clientConfig);
            fail("IOException is expected.");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains(value));
        }
        // verify ClientCnxnSocketNetty creation
        try {
            new ClientCnxnSocketNetty(clientConfig);
            fail("IOException is expected.");
        } catch (IOException e) {
            assertTrue(e.getMessage().contains(value));
        }

    }

}