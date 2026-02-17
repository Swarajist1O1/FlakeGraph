class DummyClass_98668 {
    @Test
    public void test_http_method_override() {
        Response resp = post("/common/httpmethods?_method=DELETE", new NutMap("_method", "DELETE"));
        assertEquals(200, resp.getStatus());
        assertEquals("DELETE", resp.getContent());
    }

}