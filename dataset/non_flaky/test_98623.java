class DummyClass_98623 {
    @Test
    public void test_el2() throws Exception {
        El el = new El("sayhi(name)");
        Context ctx = Lang.context();
        ctx.set("name", "wendal");
        ctx.set("sayhi", getClass().getMethod("sayhi", String.class));
        assertEquals("hi,wendal", el.eval(ctx));
    }

}