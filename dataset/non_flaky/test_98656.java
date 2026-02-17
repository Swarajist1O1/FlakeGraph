class DummyClass_98656 {
    @Test
    public void test_view_resp() {
        get("/views/resp/to/1");
        assertEquals("hi", resp.getContent());
        get("/views/resp/to/2");
        assertEquals(200, resp.getStatus());
        assertEquals("{\"name\":\"wendal\"}", resp.getContent());
    }

}