class DummyClass_98646 {
    @Test
    public void test_path_args_and_object_without_param() {
        assertEquals(200, get("/adaptor/path_args_and_object_without_param/1").getStatus());
        assertEquals("{\"name\": \"1\"}".replaceAll(" ", ""), get("/adaptor/path_args_and_object_without_param/1?name=object").getContent().replaceAll(" ", ""));
    }

}