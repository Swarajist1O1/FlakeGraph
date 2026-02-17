class DummyClass_96877 {
  @Test
  public void testLogicalTypesWithMultipleFields() throws Exception {
    Schema logicalTypesWithMultipleFields = new Schema.Parser().parse(
        new File("src/test/resources/logical_types_with_multiple_fields.avsc"));
    assertCompilesWithJavaCompiler(new File(OUTPUT_DIR.getRoot(), name.getMethodName()),
        new SpecificCompiler(logicalTypesWithMultipleFields).compile());
  }

}