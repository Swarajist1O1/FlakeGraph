class DummyClass_33837 {
    @Test
    public void testCopyFileOverIronMQ() throws Exception {
        getMockEndpoint("mock:result").expectedMessageCount(1);
        assertMockEndpointsSatisfied();
        assertFileExists("target/out/test.txt");
    }

}