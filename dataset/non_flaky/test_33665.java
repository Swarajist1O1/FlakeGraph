class DummyClass_33665 {
    @Test
    public void testBug569() {
        //ç¬¬ä¸æ¬¡ååºååæ¯ä½¿ç¨ç MyResponseï¼ æ²¡ææå®æ³åç±»åï¼è²ä¼¼ä¼ç¼å­ MyResponseï¼ åé¢å¨è°ç¨çMyResponse<?>ååºååå°±åå½±åäº
        MyResponse resp1 = JSON.parseObject(jsonData, mType1, configBug569, featureValues,
                features != null ? features : EMPTY_SERIALIZER_FEATURES);

        //expect MyResponse<JSONArray<JSONObject>>
        MyResponse resp = JSON.parseObject(jsonData, mType, configBug569, featureValues,
                features != null ? features : EMPTY_SERIALIZER_FEATURES);
        Assert.assertNotNull(resp);
        Assert.assertNotNull(resp.getResult());
        Assert.assertEquals(JSONArray.class, resp.getResult().getClass());//è¿éä¼åå° resp1 çå½±å
    }

}