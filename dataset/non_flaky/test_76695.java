class DummyClass_76695 {
    @Test
    public void t4() {
        given()
                .when().get("/hello/greeting/foo")
                .then()
                .statusCode(200)
                .body(is("hello foo"));
    }

}