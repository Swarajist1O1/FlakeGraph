class DummyClass_20987 {
    @Test
    public void testAggregatorsResponse() throws Exception {
        AggregatorsResponse response = new AggregatorsResponse();
        response.addAggregator("min");
        response.addAggregator("max");
        String r = JsonUtil.getObjectMapper().writeValueAsString(response);
        Assert.assertEquals("[\"min\",\"max\"]", r);
    }

}