class DummyClass_98622 {
    @Test
    public void test_el() {
        El el = new El("'hi,'+name");
        Context ctx = Lang.context();
        ctx.set("name", "wendal");
        assertEquals("hi,wendal", el.eval(ctx));
    }

}