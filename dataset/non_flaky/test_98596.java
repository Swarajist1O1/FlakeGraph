class DummyClass_98596 {
    @Test
    public void context() {
        Context context = Lang.context();
        List<String> list = new ArrayList<String>();
        list.add("jk");
        context.set("a", list);
        assertEquals("jk", El.eval(context, "a.get((1-1))"));
        assertEquals("jk", El.eval(context, "a.get(1-1)"));
        assertEquals("jk", El.eval(context, "a.get(0)"));

        assertTrue((Boolean) El.eval(Lang.context(), "a==null"));
        try {
            assertTrue((Boolean) El.eval(Lang.context(), "a.a"));
            fail();
        }
        catch (Exception e) {}
    }

}