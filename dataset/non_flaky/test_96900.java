class DummyClass_96900 {
  @Test
  public void testVisit13() {
    String s12 = "{\"type\": \"int\"}";
    Assert.assertEquals("\"int\".", Schemas.visit(new Schema.Parser().parse(s12),
        new TestVisitor() {
          public SchemaVisitorAction visitTerminal(Schema terminal) {
            sb.append(terminal).append('.');
            return SchemaVisitorAction.SKIP_SIBLINGS;
          }

}