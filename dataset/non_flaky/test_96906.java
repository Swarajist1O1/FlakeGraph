class DummyClass_96906 {
  @Test(expected = IllegalArgumentException.class)
  public void testIsUnresolvedSchemaError3() {
    // Namespace not "org.apache.avro.compiler".
    Schema s = SchemaBuilder.record("UnresolvedSchema")
        .prop("org.apache.avro.compiler.idl.unresolved.name", "x")
        .fields().endRecord();
    SchemaResolver.getUnresolvedSchemaName(s);
  }

}