class DummyClass_96878 {
  @Test
  public void testUnionAndFixedFields() throws Exception {
    Schema unionTypesWithMultipleFields = new Schema.Parser().parse(
        new File("src/test/resources/union_and_fixed_fields.avsc"));
    assertCompilesWithJavaCompiler(new File(this.outputFile, name.getMethodName()),
        new SpecificCompiler(unionTypesWithMultipleFields).compile());
  }

}