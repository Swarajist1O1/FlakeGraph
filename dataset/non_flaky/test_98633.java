class DummyClass_98633 {
    @Test
    public void test_err_param() {
        get("/adaptor/err/param?id=ABC");
        assertEquals(200, resp.getStatus());

        get("/adaptor/err/param/ABC");
        assertEquals(200, resp.getStatus());
    }

}