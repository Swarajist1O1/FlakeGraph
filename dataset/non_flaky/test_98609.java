class DummyClass_98609 {
    @Test
    public void testIssue279() throws InterruptedException {
        Context context = Lang.context();
        context.set("math", Maths.class);
        System.out.println(Maths.class.toString());
        assertEquals("class org.nutz.lang.Maths", El.eval(context, "math.toString()"));

        NutConf.load("org/nutz/el/issue279/279.js");
        assertEquals(El.eval("uuuid(false)"), "abc");
        assertEquals(El.eval("uuuid()"), "abc");
    }

}