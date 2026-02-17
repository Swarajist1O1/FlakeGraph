class DummyClass_96883 {
  @Test
  public void textCloningCopyDocs() {
    Schema recSchema = new Schema.Parser().parse(SCHEMA);
    Schemas.visit(recSchema, new PrintingVisitor());


    Schema trimmed = Schemas.visit(recSchema, new CloningVisitor(new CloningVisitor.PropertyCopier() {
      @Override
      public void copy(final Schema first, final Schema second) {
        Schemas.copyLogicalTypes(first, second);
        Schemas.copyAliases(first, second);
      }

}