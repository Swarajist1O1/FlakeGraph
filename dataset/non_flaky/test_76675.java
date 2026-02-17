class DummyClass_76675 {
    @Test()
    public void testSecureAccessFailure() {
        RestAssured.when().get("/secure-test").then()
                .statusCode(401);
    }

}