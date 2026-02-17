class DummyClass_98642 {
    @Test
    public void test_json_err_ctx() {
        resp = post("/adaptor/err_ctx", "{}");
        assertEquals(200, resp.getStatus());
        assertEquals("true", resp.getContent());
        
        resp = post("/adaptor/err_ctx", "{1234,3445}");
        assertEquals(200, resp.getStatus());
        assertEquals("false", resp.getContent());
    }

}