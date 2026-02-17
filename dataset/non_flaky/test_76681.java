class DummyClass_76681 {
    @Test
    public void testJaxrsPathAdminRoleSuccess() {
        RestAssured.given().auth().preemptive().basic("scott", "jb0ss")
                .when().get("/jaxrs-secured/parameterized-paths/my/banking/admin").then()
                .statusCode(200);
    }

}