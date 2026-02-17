class DummyClass_96891 {
  @Test
  public void testVisit4() {
    String s4 = "{\"type\": \"record\", \"name\": \"st1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": \"int\"}" +
        "]}";
    Assert.assertEquals("st1.!", Schemas.visit(new Schema.Parser().parse(s4), new TestVisitor()));

  }

}