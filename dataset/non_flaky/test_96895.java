class DummyClass_96895 {
  @Test(expected = UnsupportedOperationException.class)
  public void testVisit8() {
    String s8 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"cst2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"}]}}," +
        "{\"name\": \"f2\", \"type\": \"int\"}" +
        "]}";
    Schemas.visit(new Schema.Parser().parse(s8), new TestVisitor());
  }

}