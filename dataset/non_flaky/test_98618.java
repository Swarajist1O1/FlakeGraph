class DummyClass_98618 {
    @Test
    public void test_uu32_uu64(){
        Context ctx = Lang.context();
        
        El el = new El("uuid()");
        assertEquals(32, el.eval(ctx).toString().length());
        
        el = new El("uuid(32)");
        assertTrue(26 >= el.eval(ctx).toString().length());
        
        el = new El("uuid(64)");
        assertTrue(23 >= el.eval(ctx).toString().length());
    }

}