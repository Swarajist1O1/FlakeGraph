class DummyClass_98649 {
    @Test
    public void issue_1277() {
        resp = post("/adaptor/issue1277", new NutMap("agex", "124"));
        assertEquals(200, resp.getStatus());
        String str = resp.getContent();
        Issue1277 issue = Json.fromJson(Issue1277.class, str);
        assertEquals("abc", issue.name);
        assertEquals(123, issue.age);
        //assertEquals("1495667792000", resp.getContent());
    }

}