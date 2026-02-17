class DummyClass_98638 {
    @Test
    public void test_json_map_type() {
        resp = post("/adaptor/json/type", "{'abc': 123456}");
        if (resp.getStatus() != 200) {
            fail();
        }
    }

}