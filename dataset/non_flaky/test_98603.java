class DummyClass_98603 {
    @Test
    public void list() {
        Context context = Lang.context();
        List<String> list = new ArrayList<String>();
        context.set("b", list);
        assertEquals(0, El.eval(context, "b.size()"));
        list.add("");
        assertEquals(1, El.eval(context, "b.size()"));
        El.eval(context, "b.add('Q\nQ')");
        assertEquals(2, El.eval(context, "b.size()"));
    }

}