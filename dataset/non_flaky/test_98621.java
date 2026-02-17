class DummyClass_98621 {
    @Test
    public void test_map_get() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wendal", "http://wendal.net");
        List<String> list = new ArrayList<String>();
        list.add("abc");
        assertEquals("http://wendal.net", El.eval(Lang.context().set("ctx", map), "ctx['wendal']"));
        assertEquals("abc", El.eval(Lang.context().set("list", list), "list[0]"));
    }

}