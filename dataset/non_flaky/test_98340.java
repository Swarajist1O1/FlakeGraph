class DummyClass_98340 {
    @Test
    public void setTimeoutsAndCustomClient() {
        try {
            Unirest.config().connectTimeout(1000).socketTimeout(2000);
        } catch (Exception e) {
            fail();
        }

        try {
            Unirest.config().asyncClient(HttpAsyncClientBuilder.create().build());
        } catch (Exception e) {
            fail();
        }

        try {
            Unirest.config().asyncClient(HttpAsyncClientBuilder.create().build());
            Unirest.config().connectTimeout(1000).socketTimeout(2000);
            fail();
        } catch (Exception e) {
            // Ok
        }

        try {
            Unirest.config().httpClient(HttpClientBuilder.create().build());
            Unirest.config().connectTimeout(1000).socketTimeout(2000);
            fail();
        } catch (Exception e) {
            // Ok
        }
    }

}