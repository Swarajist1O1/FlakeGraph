class DummyClass_98611 {
    @Test
    public void testIssue293() {

        Context context = Lang.context();
        context.set("static", new Issue293());
        context.set("a", Issue293.class);

        assertEquals("xxx", El.eval(context, "a.printParam(a.info)"));
    }

}