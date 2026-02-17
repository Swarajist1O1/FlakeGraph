class DummyClass_20978 {
    @Test
    public void testAddDeserializationWithTimeAndTags() throws Exception {
        // @formatter:off
		String json = "{" +
						"\"operation\" : \"add\"," +
						"\"sessionId\" : \"1234\"," +
					    "\"metric\" : \"sys.cpu.user\"," +
						"\"tags\" : {" +
					       "\"tag2\" : \"value2\"," +
					       "\"tag1\" : \"value1\"" +
					    "}," +
						"\"startTime\" : \"1000\"," +
					    "\"endTime\" : \"2000\""+
					  "}";
		// @formatter:on
        WebSocketRequest request = JsonUtil.getObjectMapper().readValue(json.getBytes(), WebSocketRequest.class);
        Assert.assertNotNull(request);
        Assert.assertEquals(AddSubscription.class, request.getClass());
        Assert.assertEquals("1234", ((AddSubscription) request).getSessionId());
        Assert.assertEquals("sys.cpu.user", ((AddSubscription) request).getMetric());
        Assert.assertEquals(true, ((AddSubscription) request).getTags().isPresent());
        Map<String, String> tags = ((AddSubscription) request).getTags().get();
        Assert.assertTrue(tags.containsKey("tag1"));
        Assert.assertEquals("value1", tags.get("tag1"));
        Assert.assertTrue(tags.containsKey("tag2"));
        Assert.assertEquals("value2", tags.get("tag2"));
        Assert.assertEquals(true, ((AddSubscription) request).getStartTime().isPresent());
        long start = ((AddSubscription) request).getStartTime().get();
        Assert.assertEquals(1000L, start);
        Assert.assertEquals(true, ((AddSubscription) request).getEndTime().isPresent());
        long end = ((AddSubscription) request).getEndTime().get();
        Assert.assertEquals(2000L, end);
    }

}