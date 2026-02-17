class DummyClass_76696 {
    @Test
    public void t5() {
        given()
                .when().get("/hello/greeting/foo")
                .then()
                .statusCode(200)
                .body(is("hello foo"));
    }

}