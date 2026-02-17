class DummyClass_76685 {
    @Test
    public void testJaxrsGetPermitAll() {
        RestAssured.when().get("/jaxrs-secured/subject/unsecured").then()
                .statusCode(200)
                .body(equalTo("anonymous"));
    }

}