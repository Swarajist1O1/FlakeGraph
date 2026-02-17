class DummyClass_98658 {
    @Test
    public void test_simple(){
        get("/views/jsp");
        assertEquals("null", resp.getContent());
        get("/views/jsp2");
        assertEquals("null", resp.getContent());
        get("/views/jsp3");
        assertEquals("null", resp.getContent());
        get("/views/jsp4");
        assertEquals("null", resp.getContent());
    }

}