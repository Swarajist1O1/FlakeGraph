class DummyClass_33727 {
    @Test
    public  void test_jsonp_invalidParam() throws Exception {
        FastJsonJsonView view = new FastJsonJsonView();

        Assert.assertNotNull(view.getFastJsonConfig());
        view.setFastJsonConfig(new FastJsonConfig());
        view.setExtractValueFromSingleKeyModel(true);
        view.setDisableCaching(true);

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("callback", "-methodName");
        MockHttpServletResponse response = new MockHttpServletResponse();


        Assert.assertEquals(true, view.isExtractValueFromSingleKeyModel());


        view.render(Collections.singletonMap("doesn't matter", Collections.singletonMap("abc", "cdeä¸­æ")), request, response);
        String contentAsString = response.getContentAsString();
        Assert.assertTrue(contentAsString.startsWith("{\"abc\":\"cdeä¸­æ\"}"));

    }

}