class DummyClass_98650 {
    @Test
    public void issue_1310() {
        resp = post("/adaptor/issue1310", new NutMap("age", "123"));
        assertEquals(200, resp.getStatus());
        String str = resp.getContent();
        Issue1277 issue = Json.fromJson(Issue1277.class, str);
        assertEquals(123, issue.age);
        //assertEquals("1495667792000", resp.getContent());
    }

}