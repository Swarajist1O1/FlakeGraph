class DummyClass_76708 {
    @Test
    public void testFieldAndGetterReflectionOnEntityFromServlet() throws Exception {
        RestAssured.when().get("/core/charsetsupport").then()
                .body(is("OK"));
    }

}