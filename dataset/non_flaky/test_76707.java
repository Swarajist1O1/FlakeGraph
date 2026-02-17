class DummyClass_76707 {
    @Test
    public void excludedNative() {
        RestAssured.when()
                .get("/resources/test-resources/file.adoc")
                .then()
                .statusCode(404);

        RestAssured.when()
                .get("/resources/test-resources/excluded/unwanted.txt")
                .then()
                .statusCode(404);

        RestAssured.when()
                .get("/resources/META-INF/quarkus-native-resources.txt")
                .then()
                .statusCode(404);
    }

}