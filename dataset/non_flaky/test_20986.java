class DummyClass_20986 {
    @Test
    public void testAggregatorsResponseEmpty() throws Exception {
        AggregatorsResponse response = new AggregatorsResponse();
        String r = JsonUtil.getObjectMapper().writeValueAsString(response);
        Assert.assertEquals("[]", r);
    }

}