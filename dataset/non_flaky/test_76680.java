class DummyClass_76680 {
    @Test
    public void testJaxrsGetRoleSuccess() {
        RestAssured.given().auth().preemptive().basic("scott", "jb0ss")
                .when().get("/jaxrs-secured/rolesClass").then()
                .statusCode(200);
    }

}