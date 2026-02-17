class DummyClass_98665 {
    @Test
    public void test_req_param2() {
        get("/common/path2?key=base");
        assertEquals("base", resp.getContent());
        get("/common/path2?key=T");
        assertEquals(getContextPath(), resp.getContent());
    }

}