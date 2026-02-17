class DummyClass_20985 {
    @Test
    public void testSuggestResponse() throws Exception {
        SuggestResponse response = new SuggestResponse();
        response.addSuggestion("sys.cpu.idle");
        response.addSuggestion("sys.cpu.user");
        String r = JsonUtil.getObjectMapper().writeValueAsString(response);
        Assert.assertEquals("[\"sys.cpu.idle\",\"sys.cpu.user\"]", r);
    }

}