class DummyClass_98664 {
    @Test
    public void test_req_param() {
        get("/common/path?key=base");
        assertEquals(getContextPath(), resp.getContent());
    }

}