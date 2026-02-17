class DummyClass_33927 {
    @Test
    public void stateMachineFeedTest() throws Exception {
        mockResult.expectedMinimumMessageCount(1);
        mockError.expectedMessageCount(0);
        MockEndpoint.assertIsSatisfied(context);
    }

}