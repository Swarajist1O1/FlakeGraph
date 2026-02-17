class DummyClass_96905 {
  @Test(expected = IllegalArgumentException.class)
  public void testIsUnresolvedSchemaError2() {
    // No "UnresolvedSchema" property
    Schema s = SchemaBuilder.record("R")
        .prop("org.apache.avro.compiler.idl.unresolved.name", "x").fields().endRecord();
    SchemaResolver.getUnresolvedSchemaName(s);
  }

}