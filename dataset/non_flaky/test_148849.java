class DummyClass_148849 {
    @Test
    public void IsFromStreamingConnection() {
        ArrayList<String> nonStreaming = new ArrayList<>();
        nonStreaming.add("http://yayay.com");
        nonStreaming.add("https://yayay.com");
        nonStreaming.add("HTTP://yayay.com");
        nonStreaming.add("HTTPS://yayay.com");

        ArrayList<String> streaming = new ArrayList<>();
        streaming.add("urn:botframework:WebSocket:wss://beep.com");
        streaming.add("urn:botframework:WebSocket:http://beep.com");
        streaming.add("URN:botframework:WebSocket:wss://beep.com");
        streaming.add("URN:botframework:WebSocket:http://beep.com");

        Activity activity = createActivity();
        activity.setServiceUrl(null);

        Assert.assertFalse(activity.isFromStreamingConnection());

        nonStreaming.forEach(s ->
        {
            activity.setServiceUrl(s);
            Assert.assertFalse(activity.isFromStreamingConnection());
        });

        streaming.forEach(s ->
        {
            activity.setServiceUrl(s);
            Assert.assertTrue(activity.isFromStreamingConnection());
        });
    }

}