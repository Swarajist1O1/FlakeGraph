class DummyClass_98617 {
    @Test
    public void test_issue411(){
    	El el=new El("a[0].b.isPass('')?'1':'2'");
        Context ctx = Lang.context();
        ctx.set("a",new Object[]{new org.nutz.el.issue411.Issue411.A()} );
        assertEquals("1", el.eval(ctx));
    }

}