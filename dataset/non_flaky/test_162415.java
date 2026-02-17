class DummyClass_162415 {
    @Test
    public void testBridgedNetworkContainer() throws TimeoutException {
        String output = getContainerOutput(bridgedNetwork);

        assertTrue("'bridge' network can access the internet", output.contains("seq=1"));
    }

}