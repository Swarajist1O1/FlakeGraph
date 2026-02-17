class DummyClass_98652 {
    @Test
    public void test_localdt() {
        resp = post("/adaptor/jdk8/localdt", new NutMap("date", "2018-02-20 21:11:51"));
        assertEquals(200, resp.getStatus());
        String str = resp.getContent();
        assertEquals("2018-02-20T21:11:51", str);
    }

}