class DummyClass_76706 {
    @Test
    public void testFieldAndGetterReflectionOnEntityFromServlet() throws Exception {
        RestAssured.when().get("/core/reflection").then()
                .body(is("OK"));
    }

}