class DummyClass_96904 {
  @Test(expected = IllegalArgumentException.class)
  public void testIsUnresolvedSchemaError1() {
    // No "org.apache.avro.compiler.idl.unresolved.name" property
    Schema s = SchemaBuilder.record("R").fields().endRecord();
    SchemaResolver.getUnresolvedSchemaName(s);
  }

}