class DummyClass_20984 {
    @Test
    public void testSuggestResponseEmpty() throws Exception {
        SuggestResponse response = new SuggestResponse();
        String r = JsonUtil.getObjectMapper().writeValueAsString(response);
        Assert.assertEquals("[]", r);
    }

}