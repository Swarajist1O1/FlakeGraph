class DummyClass_96897 {
  @Test(expected = UnsupportedOperationException.class)
  public void testVisit10() {
    String s10 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"ct2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"}]}}," +
        "{\"name\": \"f2\", \"type\": \"int\"}" +
        "]}";
    Schemas.visit(new Schema.Parser().parse(s10),
        new TestVisitor() {
          public SchemaVisitorAction visitTerminal(Schema terminal) {
            return SchemaVisitorAction.SKIP_SUBTREE;
          }

}