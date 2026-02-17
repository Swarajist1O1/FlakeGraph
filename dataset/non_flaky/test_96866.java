class DummyClass_96866 {
  @Test(expected=RuntimeException.class)
  public void testCalcAllArgConstructorParameterUnitsFailure() {
    Schema nonRecordSchema = SchemaBuilder.array().items().booleanType();
    new SpecificCompiler().calcAllArgConstructorParameterUnits(nonRecordSchema);
  }

}