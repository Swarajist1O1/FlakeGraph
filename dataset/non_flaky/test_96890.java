class DummyClass_96890 {
  @Test
  public void testVisit3() {
    String s3 = "{\"type\": \"record\", \"name\": \"ss1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": \"int\"}" +
        "]}";
    Assert.assertEquals("ss1.", Schemas.visit(new Schema.Parser().parse(s3), new TestVisitor()));

  }

}