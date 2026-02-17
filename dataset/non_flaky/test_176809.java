class DummyClass_176809 {
    @Test
    public void shouldNotReturnBodyWhenEnabledButContentTypeOctet() {
        byte[] body = RandomUtils.nextBytes(20);

        HttpResponseFacade mock = mock(HttpResponseFacade.class);
        when(mock.response()).thenReturn(generateResponse(
            "application/octet-stream",
            404,
            body));

        ((HttpAssertionFacadeImpl) facade).facade = mock;
        world.put(ASSERTS_STATUS_CODE_DISPLAY_BODY, "true");
        world.put(ASSERTS_STATUS_CODE_MAX_SIZE, "5000");

        validateException(
            200,
            "1 expectation failed.\n" +
                "Expected status code \"200\" but was \"404\" with body <binary>.\n");
    }

}