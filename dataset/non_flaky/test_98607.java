class DummyClass_98607 {
    @Test
    public void testIssue277() {
        Context context = Lang.context();
        context.set("strings", Strings.class);
        assertEquals("a", El.eval(context, "strings.trim(\"  a  \")"));
    }

}