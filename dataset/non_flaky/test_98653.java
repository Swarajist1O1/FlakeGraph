class DummyClass_98653 {
    @Test
    public void test_simple() {
        get("/views/for?to=base");
        assertEquals(200, resp.getStatus());
        assertEquals(getContextPath(), resp.getContent());
        
        get("/views/for2?to=base");
        assertEquals(200, resp.getStatus());
        assertEquals(getContextPath(), resp.getContent());

        get("/views/for3?to=base");
        assertEquals(200, resp.getStatus());
        assertEquals(getContextPath(), resp.getContent());
    }

}