class DummyClass_98647 {
    @Test
    public void issue_1069() {
        resp = post("/adaptor/issue1069", "");
        assertEquals(200, resp.getStatus());
        assertEquals("", resp.getContent());
        

        resp = post("/adaptor/issue1069", "showAdd=");
        assertEquals(200, resp.getStatus());
        assertEquals("", resp.getContent());
    }

}