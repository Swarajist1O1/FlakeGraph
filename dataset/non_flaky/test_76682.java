class DummyClass_76682 {
    @Test
    public void testJaxrsPathAdminRoleFailure() {
        RestAssured.given().auth().preemptive().basic("noadmin", "n0Adm1n")
                .when().get("/jaxrs-secured/parameterized-paths/my/banking/admin").then()
                .statusCode(403);
    }

}