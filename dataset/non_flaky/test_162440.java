class DummyClass_162440 {
    @Test
    public void simpleDslTest() throws IOException {
        String address = String.format("http://%s:%s", dslContainer.getContainerIpAddress(), dslContainer.getMappedPort(80));

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(address);

        try (CloseableHttpResponse response = httpClient.execute(get)) {
            assertEquals("A container built from a dockerfile can run nginx as expected, and returns a good status code",
                            200,
                            response.getStatusLine().getStatusCode());
            assertTrue("A container built from a dockerfile can run nginx as expected, and returns an expected Server header",
                            response.getHeaders("Server")[0].getValue().contains("nginx"));
        }
    }

}