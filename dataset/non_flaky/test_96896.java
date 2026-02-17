class DummyClass_96896 {
  @Test
  public void testVisit9() {
    String s9 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"ct2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"}]}}," +
        "{\"name\": \"f2\", \"type\": \"long\"}" +
        "]}";
    Assert.assertEquals("c1.ct2.\"int\"!", Schemas.visit(new Schema.Parser().parse(s9), new TestVisitor()));
  }

}