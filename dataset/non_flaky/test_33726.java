class DummyClass_33726 {
    @Test
    public  void test_jsonp() throws Exception {
        FastJsonJsonView view = new FastJsonJsonView();

        Assert.assertNotNull(view.getFastJsonConfig());
        view.setFastJsonConfig(new FastJsonConfig());
        view.setExtractValueFromSingleKeyModel(true);
        view.setDisableCaching(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("callback", "queryName");
        MockHttpServletResponse response = new MockHttpServletResponse();


        Assert.assertEquals(true, view.isExtractValueFromSingleKeyModel());


        view.render(Collections.singletonMap("abc", "cdeä¸­æ"), request, response);
        String contentAsString = response.getContentAsString();
        int contentLength = response.getContentLength();

        Assert.assertEquals(contentLength, contentAsString.getBytes(view.getFastJsonConfig().getCharset().name()).length);
    }

}