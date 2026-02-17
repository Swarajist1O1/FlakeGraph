class DummyClass_76679 {
    @Test
    public void testJaxrsGetRoleFailure() {
        RestAssured.given().auth().preemptive().basic("jdoe", "p4ssw0rd")
                .when().get("/jaxrs-secured/rolesClass").then()
                .statusCode(403);
    }

}