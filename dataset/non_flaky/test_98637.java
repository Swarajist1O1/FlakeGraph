class DummyClass_98637 {
    @Test
    public void test_multi_err_ctxs2() {
        get("/adaptor/multi/err/ctxs2/a/b?id=ABC");
        assertEquals(200, resp.getStatus());

        get("/adaptor/multi/err/ctxs2/a/b/ABC");
        assertEquals(200, resp.getStatus());
    }

}