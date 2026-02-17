class DummyClass_162414 {
    @Test
    public void testHostNetworkContainer() throws TimeoutException {
        String output = getContainerOutput(hostNetwork);

        assertTrue("'host' network can access the internet", output.contains("seq=1"));
    }

}