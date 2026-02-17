class DummyClass_98616 {
    @Test
    public void test_issue314() {
        Context context = Lang.context();
        
        context.set("String", String.class);
        
        Issue314 i314 = new Issue314();
        List<String> list = new ArrayList<String>();
        list.add("123");
        i314.setList(list);
        context.set("map", i314);
        
        assertEquals("123", El.eval(context, "String.valueOf(123)"));
        assertEquals("123", El.eval(context, "map.list.get(0)"));
    }

}