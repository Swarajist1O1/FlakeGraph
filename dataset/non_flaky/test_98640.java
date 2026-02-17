class DummyClass_98640 {
    @Test
    public void test_reader_as_string() {
        resp = post("/adaptor/reader", "I am abc");
        if (resp.getStatus() != 200) {
            fail();
        }
        assertEquals("I am abc", resp.getContent());
    }

}