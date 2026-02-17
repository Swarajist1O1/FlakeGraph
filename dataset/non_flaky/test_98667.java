class DummyClass_98667 {
    @Test
    public void test_aop_trans_1() {
        String name = ""+System.currentTimeMillis();
        get("/aop/test1?name="+name);
        assertEquals(200, resp.getStatus());
        get("/aop/test1/result?name="+name);
        assertEquals(200, resp.getStatus());
        assertEquals("0", resp.getContent());
    }

}