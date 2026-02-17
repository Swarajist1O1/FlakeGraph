class DummyClass_33672 {
    @Test
    public void parseWithExistType() {
        String s = "{\"@type\":\"com.alibaba.fastjson.deserializer.ValueBean\",\"val\":1}";
        Object object = JSONObject.parse(s);
        Assert.assertNotNull(object);
        Assert.assertTrue(object instanceof ValueBean);
        Assert.assertEquals(new Integer(1), ValueBean.class.cast(object).getVal());
    }

}