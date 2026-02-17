class DummyClass_96898 {
  @Test
  public void testVisit11() {
    String s11 = "{\"type\": \"record\", \"name\": \"c1\", \"fields\": [" +
        "{\"name\": \"f1\", \"type\": {\"type\": \"record\", \"name\": \"c2\", \"fields\": " +
        "[{\"name\": \"f11\", \"type\": \"int\"},{\"name\": \"f12\", \"type\": \"double\"}" +
        "]}}," +
        "{\"name\": \"f2\", \"type\": \"long\"}" +
        "]}";
    Assert.assertEquals("c1.c2.\"int\".!\"long\".!", Schemas.visit(new Schema.Parser().parse(s11),
        new TestVisitor() {
          public SchemaVisitorAction visitTerminal(Schema terminal) {
            sb.append(terminal).append('.');
            return SchemaVisitorAction.SKIP_SIBLINGS;
          }

}