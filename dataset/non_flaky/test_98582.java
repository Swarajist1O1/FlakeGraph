class DummyClass_98582 {
    @Test
    public void test2(){
        String[] a = new String[]{"a","b"};
        String[] b = new String[]{"1","2"};
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a", a);
        map.put("b", b);
        El exp = new El("util.test(map['a'][0],map['b'][0])");  // é¢ç¼è¯ç»æä¸ºä¸ä¸ª El å¯¹è±¡
        Context context = Lang.context();
        context.set("util",new StringUtil());
        context.set("map", map);
        System.out.println(exp.eval(context));
    }

}