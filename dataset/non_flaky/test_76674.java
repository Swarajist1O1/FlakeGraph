class DummyClass_76674 {
    @Test
    public void testImport() {
        RestAssured.when().get("/my-entity/1").then().body(is("MyEntity:TEST ENTITY"));
    }

}