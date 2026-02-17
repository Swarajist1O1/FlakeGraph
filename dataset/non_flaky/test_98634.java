class DummyClass_98634 {
    @Test
    public void test_err_param_anywhere() {
        get("/adaptor/err/param/anywhere?id=ABC");
        assertEquals(200, resp.getStatus());

        get("/adaptor/err/param/anywhere/ABC");
        assertEquals(200, resp.getStatus());
    }

}