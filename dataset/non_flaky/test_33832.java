class DummyClass_33832 {
    @Test
    public void testAsyncStress() throws Exception {
        // do not test on windows
        assumeFalse(isPlatform("windows"));

        // test by starting the unit test FileAsyncStressFileDropper in another
        // JVM

        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(250);

        assertMockEndpointsSatisfied();
    }

}