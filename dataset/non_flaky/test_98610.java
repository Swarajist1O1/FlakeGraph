class DummyClass_98610 {
    @Test
    public void testIssue292() {
        Context context = Lang.context();
        context.set("a", 123);
        context.set("b", 20);
        Object o = El.eval(context, "a>b?a:b");
        assertEquals(123, o);
    }

}