class DummyClass_98654 {
    @Test
    public void test_raw() {
        get("/views/raw");
        assertEquals("ABC", resp.getContent());

        get("/views/raw2");
        assertEquals(3, resp.getContent().length());

        get("/views/raw3");
        assertEquals(3, resp.getContent().length());

        get("/views/raw4");
        assertEquals("", resp.getContent());

        get("/views/raw5");
        assertTrue(resp.getHeader().get("Content-Type").startsWith("application/json"));
    }

}