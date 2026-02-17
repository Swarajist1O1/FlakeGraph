class DummyClass_98597 {
    @Test
    public void array() {
        Context context = Lang.context();
        String[] str = new String[]{"a", "b", "c"};
        String[][] bb = new String[][]{{"a", "b"}, {"c", "d"}};
        context.set("a", str);
        context.set("b", bb);
        assertEquals("b", El.eval(context, "a[1]"));
        assertEquals("b", El.eval(context, "a[1].toString()"));
        assertEquals("b", El.eval(context, "a[2-1]"));
        assertEquals("d", El.eval(context, "b[1][1]"));
    }

}