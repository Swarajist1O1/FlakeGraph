class DummyClass_96879 {
  @Test
  public void testLogicalTypesWithMultipleFieldsJsr310DateTime() throws Exception {
    Schema logicalTypesWithMultipleFields = new Schema.Parser().parse(
        new File("src/test/resources/logical_types_with_multiple_fields.avsc"));
    assertCompilesWithJavaCompiler(new File(this.outputFile, name.getMethodName()),
        new SpecificCompiler(logicalTypesWithMultipleFields, JSR310).compile());
  }

}