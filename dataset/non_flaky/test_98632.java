class DummyClass_98632 {
    @Test
    public void test_issue_543() {
        get("/adaptor/github/issue/543?d=20120924");
        assertEquals(200, resp.getStatus());

        long ms = Times.ams("2012-09-24", TimeZone.getTimeZone("Asia/Shanghai"));
        long rems = Long.parseLong(resp.getContent());
        assertEquals(ms, rems);
    }

}