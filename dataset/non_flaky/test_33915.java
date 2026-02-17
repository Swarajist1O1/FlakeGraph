class DummyClass_33915 {
    @Test
    public void testCustomFileQuery() throws Exception {
        Map<String, Object> headers = new HashMap<>();
        headers.put(Exchange.FILE_NAME, FILE_NAME);

        Exchange result = template.request(
                "mongodb-gridfs:myDb?database={{mongodb.testDb}}&operation=create&bucket=customFileFilterTest",
                new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setBody(FILE_DATA);
                        exchange.getMessage().setHeaders(headers);
                    }

}