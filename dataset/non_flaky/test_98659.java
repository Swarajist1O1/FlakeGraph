class DummyClass_98659 {
    @Test
    public void test_issue_1212() {
        get("/mapping/issue1212/sayhi");
        assertEquals(200, resp.getStatus());
    }

}