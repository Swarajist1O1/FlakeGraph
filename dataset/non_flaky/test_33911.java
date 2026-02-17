class DummyClass_33911 {
    @Test
    public void testRemoveByObjectId() {
        Map<String, Object> headers = new HashMap<>();
        headers.put(Exchange.FILE_NAME, FILE_NAME);

        Exchange result = template.request(
                "mongodb-gridfs:myDb?database={{mongodb.testDb}}&operation=create&bucket=" + getBucket(), new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        exchange.getMessage().setBody(FILE_DATA);
                        exchange.getMessage().setHeaders(headers);
                    }

}