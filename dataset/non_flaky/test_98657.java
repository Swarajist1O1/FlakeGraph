class DummyClass_98657 {
    @Test
    public void test_simple() {
        get("/views/red?to=base");
        assertEquals(200, resp.getStatus());
        assertEquals(getContextPath(), resp.getContent());
        
        get("/views/red2?to=base");
        assertEquals(200, resp.getStatus());
        assertEquals(getContextPath(), resp.getContent());

        get("/views/red3?to=base");
        assertEquals(200, resp.getStatus());
        assertEquals(getContextPath(), resp.getContent());
    }

}