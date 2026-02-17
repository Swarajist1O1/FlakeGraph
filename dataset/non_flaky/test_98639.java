class DummyClass_98639 {
    @Test
    public void test_inputstream_as_string() {
        resp = post("/adaptor/ins", "I am abc");
        if (resp.getStatus() != 200) {
            fail();
        }
        assertEquals("I am abc", resp.getContent());
    }

}