class DummyClass_20973 {
    @Test
    public void testCreateDeserialization() throws Exception {
        // @formatter:off
		String json = "{ "
				       + "\"operation\" : \"create\","
				       + " \"sessionId\": \"1234\""
				    + "}";
		// @formatter:on
        WebSocketRequest request = JsonUtil.getObjectMapper().readValue(json.getBytes(), WebSocketRequest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(CreateSubscription.class, request.getClass());
        Assert.assertEquals("1234", ((CreateSubscription) request).getSessionId());
    }

}