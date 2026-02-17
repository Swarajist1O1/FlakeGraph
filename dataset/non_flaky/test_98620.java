class DummyClass_98620 {
    @Test
    public void test_urlencode() throws UnsupportedEncodingException {
        String re = El.eval("urlencode('ä¸­æ')").toString();
        assertEquals(URLEncoder.encode("ä¸­æ", Encoding.UTF8), re);
        
        re = El.eval("urlencode('ä¸­æ', 'gbk')").toString();
        assertEquals(URLEncoder.encode("ä¸­æ", Encoding.GBK), re);
        
        re = El.eval("urlencode('ä¸­æ', 'gb2312')").toString();
        assertEquals(URLEncoder.encode("ä¸­æ", Encoding.GB2312), re);
    }

}