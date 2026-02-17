class DummyClass_98666 {
    @Test
    public void test_servlet_obj() {
        get("/common/servlet_obj");
        assertEquals(200, resp.getStatus());
    }

}