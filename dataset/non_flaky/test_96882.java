class DummyClass_96882 {
  @Test
  public void textCloning() {
    Schema recSchema = new Schema.Parser().parse(SCHEMA);
    Schemas.visit(recSchema, new PrintingVisitor());


    CloningVisitor cv = new CloningVisitor(recSchema);
    Schema trimmed = Schemas.visit(recSchema, cv);
    Assert.assertNull(trimmed.getDoc());
    Assert.assertNotNull(recSchema.getDoc());

    SchemaCompatibility.SchemaCompatibilityType compat =
        SchemaCompatibility.checkReaderWriterCompatibility(trimmed, recSchema).getType();
    Assert.assertEquals(SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE, compat);
    compat = SchemaCompatibility.checkReaderWriterCompatibility(recSchema, trimmed).getType();
    Assert.assertEquals(SchemaCompatibility.SchemaCompatibilityType.COMPATIBLE, compat);
    Assert.assertNotNull(cv.toString());
  }

}