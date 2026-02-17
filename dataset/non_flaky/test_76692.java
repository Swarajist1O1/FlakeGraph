class DummyClass_76692 {
    @Test
    public void t1() {
        given()
                .when().get("/hello/greeting/foo")
                .then()
                .statusCode(200)
                .body(is("hello foo"));
    }

}