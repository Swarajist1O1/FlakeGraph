class DummyClass_33833 {
    @Test
    public void testDropInNewFiles() throws Exception {
        // do not test on windows
        assumeFalse(isPlatform("windows"));

        MockEndpoint mock = getMockEndpoint("mock:result");
        mock.expectedMinimumMessageCount(250);

        assertMockEndpointsSatisfied();
    }

}