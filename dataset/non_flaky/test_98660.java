class DummyClass_98660 {
    @Test
    public void test_json_adaptor() {
        post("/adaptor/json/pet/array", "{pets:[{name:'zzh'},{name:'wendal'}]}");
        assertEquals("pets(2) array", resp.getContent());

        post("/adaptor/json/pet/list", "{pets:[{name:'zzh'},{name:'wendal'}]}");
        assertEquals("pets(2) list", resp.getContent());
    }

}