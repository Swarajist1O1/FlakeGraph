class DummyClass_76693 {
    @Test
    public void t2() {
        given()
                .when().get("/hello/greeting/foo")
                .then()
                .statusCode(200)
                .body(is("hello foo"));
    }

}