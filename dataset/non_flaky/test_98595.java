class DummyClass_98595 {
    @Test
    public void test_simple_condition() {
        Context context = Lang.context();
        context.set("a", 10);
        assertEquals(10, El.eval(context, "a"));
        assertEquals(20, El.eval(context, "a + a"));

        context.set("b", "abc");
        assertEquals(25, El.eval(context, "a + 2 +a+ b.length()"));

        String s = "a>5?'GT 5':'LTE 5'";
        assertEquals("GT 5", El.eval(context, s));
        context.set("a", 5);
        assertEquals("LTE 5", El.eval(context, s));

        assertEquals("jk", El.eval("\"j\"+\"k\""));

    }

}