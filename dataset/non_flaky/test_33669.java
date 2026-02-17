class DummyClass_33669 {
    @Test(expected = JSONException.class)
    public void parseObjectWithNotExistTypeThrowException() {
        String s = "{\"@type\":\"com.alibaba.fastjson.ValueBean\",\"val\":1}";
        JSONObject.parseObject(s, ValueBean.class);
    }

}