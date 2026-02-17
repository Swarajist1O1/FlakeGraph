class DummyClass_98581 {
    @Test
    public void test() throws InstantiationException, IllegalAccessException{
        String[] a = new String[]{"a","b"};
        Map<String, String[]> map = new HashMap<String, String[]>();
        map.put("a", a);
        El exp = new El("util.test(map['a'])");
        Context context = Lang.context();
        context.set("util",StringUtil.class.newInstance());
        context.set("map", map);
        assertEquals("ab", exp.eval(context));
    }

}