class DummyClass_98598 {
    @Test
    public void field() {
        class abc {
            @SuppressWarnings("unused")
            public String name = "jk";
        }
        Context context = Lang.context();
        context.set("a", new abc());
        assertEquals("jk", El.eval(context, "a.name"));
        // è¿ä¸ªåè½æ¾å¼
        // assertFalse((Boolean)El.eval("java.lang.Boolean.FALSE"));
        // assertFalse((Boolean)El.eval("Boolean.FALSE"));
    }

}