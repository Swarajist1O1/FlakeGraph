class DummyClass_98661 {
    @Test
    public void test_base() {
        get("/base.jsp");
        assertNotNull(resp);
        assertEquals(200, resp.getStatus());
        assertEquals(getContextPath(), resp.getContent());
    }

}