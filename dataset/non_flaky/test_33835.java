class DummyClass_33835 {
    @Test
    public void testSendMessagesBetweenQueues() throws Exception {
        getMockEndpoint("mock:result").expectedMessageCount(100);
        for (int i = 1; i <= 100; i++) {
            String payloadToSend = PAYLOAD.replace("#", "" + i);
            template.sendBody("direct:start", payloadToSend);
        }
        assertMockEndpointsSatisfied(2, TimeUnit.MINUTES);
    }

}