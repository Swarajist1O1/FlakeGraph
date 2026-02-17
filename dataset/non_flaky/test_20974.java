class DummyClass_20974 {
    @Test
    public void testRemoveDeserialization() throws Exception {
        // @formatter:off
		String json = "{ "
				       + "\"operation\" : \"remove\","
				       + " \"sessionId\": \"1234\","
				       + " \"metric\" : \"sys.cpu.user\""
				    + "}";
		// @formatter:on
        WebSocketRequest request = JsonUtil.getObjectMapper().readValue(json.getBytes(), WebSocketRequest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(RemoveSubscription.class, request.getClass());
        Assert.assertEquals("1234", ((RemoveSubscription) request).getSessionId());
        Assert.assertEquals("sys.cpu.user", ((RemoveSubscription) request).getMetric());
    }

}