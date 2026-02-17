class DummyClass_76714 {
    @Test
    public void testFieldAndGetterReflectionOnEntityFromServlet() throws Exception {
        RestAssured.when().get("/jpa/testreflection").then()
                .body(is("OK"));
    }

}