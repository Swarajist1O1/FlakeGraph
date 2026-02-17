class DummyClass_76687 {
    @Test
    public void testJaxrsGetDenyAllWithAuth() {
        RestAssured.given().auth().preemptive().basic("scott", "jb0ss")
                .when().get("/jaxrs-secured/subject/denied").then()
                .statusCode(403);
    }

}