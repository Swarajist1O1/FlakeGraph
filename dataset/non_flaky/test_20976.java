class DummyClass_20976 {
    @Test
    public void testAddDeserialization() throws Exception {
        // @formatter:off
		String json = "{" +
						"\"operation\" : \"add\"," +
						"\"sessionId\" : \"1234\"," +
					    " \"metric\" : \"sys.cpu.user\"" +
					  "}";
		// @formatter:on
        WebSocketRequest request = JsonUtil.getObjectMapper().readValue(json.getBytes(), WebSocketRequest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(AddSubscription.class, request.getClass());
        Assert.assertEquals("1234", ((AddSubscription) request).getSessionId());
        Assert.assertEquals("sys.cpu.user", ((AddSubscription) request).getMetric());
        Assert.assertEquals(false, ((AddSubscription) request).getTags().isPresent());
        Assert.assertEquals(false, ((AddSubscription) request).getStartTime().isPresent());
    }

}