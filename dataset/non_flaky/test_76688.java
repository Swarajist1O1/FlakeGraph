class DummyClass_76688 {
    @Test
    public void testTracer() {
        RestAssured.when().get("/tracer").then()
                .statusCode(200)
                .body(is("Hello Tracer!"));
    }

}