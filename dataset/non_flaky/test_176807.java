class DummyClass_176807 {
    @Test
    public void shouldNotReturnBodyWhenDisabled() {
        String body = "{\n" +
            "  \"error\": \"not found\"\n" +
            "}";

        HttpResponseFacade mock = mock(HttpResponseFacade.class);
        when(mock.response()).thenReturn(generateResponse(
            "application/json",
            404,
            body.getBytes()));

        ((HttpAssertionFacadeImpl) facade).facade = mock;
        world.put(ASSERTS_STATUS_CODE_DISPLAY_BODY, "false");
        world.put(ASSERTS_STATUS_CODE_MAX_SIZE, "100");

        validateException(
            200,
            "1 expectation failed.\n" +
                "Expected status code \"200\" but was \"404\".\n");
    }

}