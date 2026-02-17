class DummyClass_96864 {
  @Test
  public void testInvalidParameterCounts() throws Exception {
    Schema invalidSchema1 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT + 1, 0);
    SpecificCompiler compiler = new SpecificCompiler(invalidSchema1);
    assertCompilesWithJavaCompiler(new File(OUTPUT_DIR.getRoot(), name.getMethodName() + "1"), compiler.compile());

    Schema invalidSchema2 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT, 10);
    compiler = new SpecificCompiler(invalidSchema2);
    assertCompilesWithJavaCompiler(new File(OUTPUT_DIR.getRoot(), name.getMethodName() + "2"), compiler.compile());
  }

}