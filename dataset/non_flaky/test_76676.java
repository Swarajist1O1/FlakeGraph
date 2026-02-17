class DummyClass_76676 {
    @Test()
    public void testSecureRoleFailure() {
        RestAssured.given().auth().preemptive().basic("jdoe", "p4ssw0rd")
                .when().get("/secure-test").then()
                .statusCode(403);
    }

}