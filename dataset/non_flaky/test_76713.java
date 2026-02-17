class DummyClass_76713 {
    @Test
    public void testEntitySerializationFromServlet() throws Exception {
        RestAssured.when().get("/core/serialization").then()
                .body(is("OK"));
    }

}