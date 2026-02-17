class DummyClass_76702 {
    @Test
    public void bar() {
        given()
                .when().get("/hello/greeting/foo")
                .then()
                .statusCode(200)
                .body(is("hello foo"));
    }

}