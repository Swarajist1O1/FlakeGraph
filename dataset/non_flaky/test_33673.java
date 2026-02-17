class DummyClass_33673 {
    @Test
    public void parseObjectWithExistType() {
        String s = "{\"@type\":\"com.alibaba.fastjson.deserializer.ValueBean\",\"val\":1}";
        ValueBean object = JSONObject.parseObject(s, ValueBean.class);
        Assert.assertNotNull(object);
        Assert.assertEquals(new Integer(1), object.getVal());
    }

}