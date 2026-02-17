class DummyClass_162413 {
    @Test
    public void testNoNetworkContainer() throws TimeoutException {
        String output = getContainerOutput(noNetwork);

        assertTrue("'none' network causes a network access error", output.contains("bad address"));
    }

}