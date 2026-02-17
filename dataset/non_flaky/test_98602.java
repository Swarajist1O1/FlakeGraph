class DummyClass_98602 {
    @Test
    public void map() {
        Context context = Lang.context();
        context.set("a", Lang.map("{x:10,y:50,txt:'Hello'}"));

        assertEquals(100, El.eval(context, "a.get('x')*10"));
        assertEquals(100, El.eval(context, "a.x*10"));
        assertEquals(100, El.eval(context, "a['x']*10"));
        assertEquals("Hello-40", El.eval(context, "a.get('txt')+(a.get('x')-a.get('y'))"));
    }

}