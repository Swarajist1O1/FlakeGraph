class DummyClass_33670 {
    @Test
    public void parseObjectWithNotExistType() {
        String s = "{\"@type\":\"com.alibaba.fastjson.ValueBean\",\"val\":1}";
        ValueBean v = JSONObject.parseObject(s, ValueBean.class, Feature.IgnoreAutoType);
        Assert.assertNotNull(v);
        Assert.assertEquals(new Integer(1), v.getVal());
    }

}