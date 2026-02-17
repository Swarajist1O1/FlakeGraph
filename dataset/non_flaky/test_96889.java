class DummyClass_96889 {
  @Test
  public void testVisit2() {
    String s2 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": \"int\"}" +
        "]}";
    Assert.assertEquals("c1.\"int\"!", Schemas.visit(new Schema.Parser().parse(s2), new TestVisitor()));

  }

}