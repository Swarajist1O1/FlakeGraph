class DummyClass_98651 {
    @Test
    public void re_view_with_NutMap() {
        resp = post("/adaptor/issue13xx", new NutMap("age", "123"));
        assertEquals(200, resp.getStatus());
        String str = resp.getContent();
        assertEquals(Json.toJson(new NutMap("id", 1), JsonFormat.compact()), str);
    }

}