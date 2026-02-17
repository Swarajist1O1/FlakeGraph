class DummyClass_98645 {
    @Test
    public void test_object_without_param() {
        assertEquals(200, get("/adaptor/object_without_param").getStatus());
        assertEquals("{\"name\": \"object\"}".replaceAll(" ", ""), get("/adaptor/object_without_param?name=object").getContent().replaceAll(" ", ""));
    }

}