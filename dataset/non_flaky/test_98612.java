class DummyClass_98612 {
    @Test
    public void testIssue303() {
        Context context = Lang.context();
        Issue303 item = new Issue303("item");
        item.child = new Issue303("child");
        context.set("item", item);

        assertEquals("child", El.eval(context, "item.child.getName()"));
        assertEquals(0, El.eval(context, "item.list.size()"));
    }

}