class DummyClass_96863 {
  @Test
  public void testMaxValidParameterCounts() throws Exception {
    Schema validSchema1 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT, 0);
    assertCompilesWithJavaCompiler(new File(OUTPUT_DIR.getRoot(), name.getMethodName() + "1"), new SpecificCompiler(validSchema1).compile());

    Schema validSchema2 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT - 2, 1);
    assertCompilesWithJavaCompiler(new File(OUTPUT_DIR.getRoot(), name.getMethodName() + "2"), new SpecificCompiler(validSchema1).compile());
  }

}