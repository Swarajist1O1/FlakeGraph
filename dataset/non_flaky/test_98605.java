class DummyClass_98605 {
    @Test
    public void testIssues87() {
        Context context = Lang.context();
        context.set("a", new BigDecimal("7"));
        context.set("b", new BigDecimal("3"));
        assertEquals(10, El.eval(context, "a.add(b).intValue()"));
    }

}