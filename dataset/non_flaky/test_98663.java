class DummyClass_98663 {
    @Test
    public void test_param() {
        get("/common/param?id=" + Long.MAX_VALUE);
        assertEquals("" + Long.MAX_VALUE, resp.getContent());
    }

}