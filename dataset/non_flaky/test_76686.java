class DummyClass_76686 {
    @Test
    public void testJaxrsGetDenyAllWithoutAuth() {
        RestAssured.when().get("/jaxrs-secured/subject/denied").then()
                .statusCode(401);
    }

}