class DummyClass_96865 {
  @Test
  public void testMaxParameterCounts() throws Exception {
    Schema validSchema1 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT, 0);
    assertTrue(new SpecificCompiler(validSchema1).compile().size() > 0);

    Schema validSchema2 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT - 2, 1);
    assertTrue(new SpecificCompiler(validSchema2).compile().size() > 0);

    Schema validSchema3 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT - 1, 1);
    assertTrue(new SpecificCompiler(validSchema3).compile().size() > 0);

    Schema validSchema4 = createSampleRecordSchema(SpecificCompiler.MAX_FIELD_PARAMETER_UNIT_COUNT + 1, 0);
    assertTrue(new SpecificCompiler(validSchema4).compile().size() > 0);
  }

}