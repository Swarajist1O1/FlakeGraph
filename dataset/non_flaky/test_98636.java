class DummyClass_98636 {
    @Test
    public void test_multi_err_ctxs() {
        get("/adaptor/multi/err/ctxs/a?id=ABC");
        assertEquals(200, resp.getStatus());

        get("/adaptor/multi/err/ctxs/a/ABC");
        assertEquals(200, resp.getStatus());
    }

}