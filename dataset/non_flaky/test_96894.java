class DummyClass_96894 {
  @Test
  public void testVisit7() {
    String s7 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"css2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"}]}}," +
        "{\"name\": \"f2\", \"type\": \"long\"}" +
        "]}";
    Assert.assertEquals("c1.css2.\"int\"!!",
        Schemas.visit(new Schema.Parser().parse(s7), new TestVisitor()));
  }

}