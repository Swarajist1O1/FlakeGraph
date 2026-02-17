class DummyClass_20975 {
    @Test
    public void testCloseDeserialization() throws Exception {
        // @formatter:off
		String json = "{ "
				       + "\"operation\" : \"close\","
				       + " \"sessionId\": \"1234\""
				    + "}";
		// @formatter:on
        WebSocketRequest request = JsonUtil.getObjectMapper().readValue(json.getBytes(), WebSocketRequest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(CloseSubscription.class, request.getClass());
        Assert.assertEquals("1234", ((CloseSubscription) request).getSessionId());
    }

}