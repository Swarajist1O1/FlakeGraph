class DummyClass_33929 {
    @Test
    public void startTrackedFlowDynamicTest() throws Exception {
        //Expects CamelFlow is deployed on the node
        mockResult.expectedMinimumMessageCount(1);
        mockError.expectedMessageCount(0);
        MockEndpoint.assertIsSatisfied(context);
        assertEquals("Hello world!", mockResult.getExchanges().get(0).getIn().getBody());
    }

}