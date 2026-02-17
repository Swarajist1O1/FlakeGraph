class DummyClass_33930 {
    @Test
    public void currentNodeTimeTest() throws Exception {
        Exchange exchange = createExchangeWithBodyAndHeader(null, OPERATION, CURRENT_NODE_TIME);
        template.send(exchange);
        Object body = exchange.getIn().getBody();
        assertNotNull(body);
        Object exception = exchange.getException();
        assertNull(exception);
    }

}