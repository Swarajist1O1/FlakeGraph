class DummyClass_20988 {
    @Test
    public void testEmptyResponse() throws Exception {
        String r = JsonUtil.getObjectMapper().writeValueAsString(Collections.emptyList());
        Assert.assertEquals("[]", r);
    }

}