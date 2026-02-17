class DummyClass_176803 {
    @Test
    public void testOutputStream() throws UnsupportedEncodingException {
        when(world.get(LOGGING_REQUEST_INCLUDES, "")).thenReturn("all");

        RequestSpecification specification = RestAssured.given()
            .config(config.getConfig())
            .baseUri("http://google.com")
            .param("q", "hi");

        plugin.beforeRequest(specification);

        specification.get();

        String requestLog = testOut.toString("UTF-8");
        assertThat(requestLog, is(EXPECTED_RESULT));
    }

}