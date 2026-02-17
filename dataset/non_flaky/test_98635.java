class DummyClass_98635 {
    @Test
    public void test_err_param_with_pathargs() {
        get("/adaptor/err/param/pathargs/a?id=ABC");
        assertEquals(200, resp.getStatus());

        get("/adaptor/err/param/pathargs/a/ABC");
        assertEquals(200, resp.getStatus());
    }

}