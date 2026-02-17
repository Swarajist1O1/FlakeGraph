class DummyClass_33671 {
    @Test
    public void parseWithNotExistType() {
        String s = "{\"@type\":\"com.alibaba.fastjson.ValueBean\",\"val\":1}";
        Object object = JSONObject.parse(s);
        Assert.assertNotNull(object);
        Assert.assertTrue(object instanceof JSONObject);
        Assert.assertEquals(new Integer(1), JSONObject.class.cast(object).getInteger("val"));
    }

}