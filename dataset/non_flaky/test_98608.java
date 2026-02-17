class DummyClass_98608 {
    @Test
    public void testIssue277_2() {
        Context context = Lang.context();
        context.set("math", Maths.class);
        assertEquals(2, El.eval(context, "math.max(1, 2)"));
    }

}