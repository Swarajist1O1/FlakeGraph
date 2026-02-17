class DummyClass_98619 {
    @Test
    public void test_base64(){
        Context ctx = Lang.context();
        
        El el = new El("base64('ä¸­æ,è±æabc,ç«ææ((%&(*')");
        assertEquals(Base64.encodeToString("ä¸­æ,è±æabc,ç«ææ((%&(*".getBytes(Encoding.CHARSET_UTF8), false), el.eval(ctx));
        
        String str = Base64.encodeToString("EEEä¸­æ".getBytes(Encoding.CHARSET_UTF8), false);
        el = new El("base64('decode', \'" + str + "\')");
        assertEquals("EEEä¸­æ", el.eval(ctx));
    }

}