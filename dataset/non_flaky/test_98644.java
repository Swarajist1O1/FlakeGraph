class DummyClass_98644 {
    @Test
    public void test_array_without_param() {
        assertEquals(200, get("/adaptor/param_without_param").getStatus());
        assertEquals("[\"1\", \"2\", \"4\", \"3\"]".replaceAll(" ", ""), get("/adaptor/param_without_param?uids=1,2,4,3").getContent().replaceAll(" ", ""));
    }

}