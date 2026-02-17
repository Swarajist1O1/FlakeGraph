class DummyClass_96886 {
  @Test
  public void testHasGeneratedJavaClass() {
    Assert.assertTrue(Schemas.hasGeneratedJavaClass(
        new Schema.Parser().parse("{\"type\": \"fixed\", \"name\": \"N\", \"size\": 10}")));
    Assert.assertFalse(Schemas.hasGeneratedJavaClass(new Schema.Parser().parse("{\"type\": \"int\"}")));
  }

}