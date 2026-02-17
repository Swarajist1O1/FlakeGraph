class DummyClass_91577 {
    @Test
    public void foo() throws IOException {
        HashMap a = new HashMap<String, String>();
        a.put("1", "1");
        a.put("3", "3");
        a.put("2", "2");


        JacksonBean bean = new JacksonBean();
        bean.setA("valuea");
        bean.setConfiguration(a);

        String s = JsonUtil.writeValueAsString(bean);
        System.out.println(s);

        JacksonBean desBean = (JacksonBean) JsonUtil.readValue("{\"a\":\"valuea\",\"b\":0,\"configuration\":{\"2\":\"2\",\"3\":\"3\",\"1\":\"1\"}}", JacksonBean.class);
        
        String x2 = JsonUtil.writeValueAsString(desBean);
        System.out.println(x2);
        
        System.out.println(desBean);
    }

}