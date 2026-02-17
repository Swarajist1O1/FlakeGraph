class DummyClass_96887 {
  @Test
  public void testGetJavaClassName() {
    Assert.assertEquals("N", Schemas.getJavaClassName(
        new Schema.Parser().parse("{\"type\": \"fixed\", \"name\": \"N\", \"size\": 10}")));
    Assert.assertEquals("N", Schemas.getJavaClassName(
        new Schema.Parser().parse("{\"type\": \"fixed\", \"name\": \"N\", \"size\": 10, \"namespace\": \"\"}")));
    Assert.assertEquals("com.example.N", Schemas.getJavaClassName(
        new Schema.Parser().parse("{\"type\": \"fixed\", \"name\": \"N\", \"size\": 10, \"namespace\": \"com.example\"}")));
  }

}