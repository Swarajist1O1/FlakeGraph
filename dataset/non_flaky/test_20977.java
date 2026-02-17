class DummyClass_20977 {
    @Test
    public void testAddDeserializationWithTime() throws Exception {
        // @formatter:off
		String json = "{" +
						"\"operation\" : \"add\"," +
						"\"sessionId\" : \"1234\"," +
					    "\"metric\" : \"sys.cpu.user\"," +
						"\"startTime\" : \"1000\"" +
					  "}";
		// @formatter:on
        WebSocketRequest request = JsonUtil.getObjectMapper().readValue(json.getBytes(), WebSocketRequest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(AddSubscription.class, request.getClass());
        Assert.assertEquals("1234", ((AddSubscription) request).getSessionId());
        Assert.assertEquals("sys.cpu.user", ((AddSubscription) request).getMetric());
        Assert.assertEquals(false, ((AddSubscription) request).getTags().isPresent());
        Assert.assertEquals(true, ((AddSubscription) request).getStartTime().isPresent());
        long time = ((AddSubscription) request).getStartTime().get();
        Assert.assertEquals(1000L, time);
    }

}