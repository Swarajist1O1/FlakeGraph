class DummyClass_33666 {
    @Test
    public void testFixBug569() {
        MyResponse resp1 = JSON.parseObject(jsonData, mType1, config, featureValues,
                features != null ? features : EMPTY_SERIALIZER_FEATURES);

        //expect MyResponse<List<Dept>>
        MyResponse resp = JSON.parseObject(jsonData, mType, config, featureValues,
                features != null ? features : EMPTY_SERIALIZER_FEATURES);
        Assert.assertNotNull(resp);
        Assert.assertNotNull(resp.getResult());
        Assert.assertEquals(ArrayList.class, resp.getResult().getClass());
    }

}