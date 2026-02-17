class DummyClass_96899 {
  @Test
  public void testVisit12() {
    String s12 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"ct2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"}]}}," +
        "{\"name\": \"f2\", \"type\": \"long\"}" +
        "]}";
    Assert.assertEquals("c1.ct2.\"int\".", Schemas.visit(new Schema.Parser().parse(s12),
        new TestVisitor() {
          public SchemaVisitorAction visitTerminal(Schema terminal) {
            sb.append(terminal).append('.');
            return SchemaVisitorAction.TERMINATE;
          }

}