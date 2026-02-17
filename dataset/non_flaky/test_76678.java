class DummyClass_76678 {
    @Test
    public void testJaxrsGetFailure() {
        RestAssured.when().get("/jaxrs-secured/rolesClass").then()
                .statusCode(401);
    }

}