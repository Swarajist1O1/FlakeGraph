class DummyClass_76683 {
    @Test
    public void testJaxrsPathUserRoleSuccess() {
        RestAssured.given().auth().preemptive().basic("stuart", "test")
                .when().get("/jaxrs-secured/parameterized-paths/my/banking/view").then()
                .statusCode(200);
    }

}