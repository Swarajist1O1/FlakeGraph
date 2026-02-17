class DummyClass_96881 {
  @Test
  public void testConversionInstanceWithDecimalLogicalTypeEnabled() throws Exception {
    SpecificCompiler compiler = createCompiler();
    compiler.setEnableDecimalLogicalType(true);

    Schema dateSchema = LogicalTypes.date()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timeSchema = LogicalTypes.timeMillis()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timestampSchema = LogicalTypes.timestampMillis()
        .addToSchema(Schema.create(Schema.Type.LONG));
    Schema decimalSchema = LogicalTypes.decimal(9,2)
        .addToSchema(Schema.create(Schema.Type.BYTES));
    Schema uuidSchema = LogicalTypes.uuid()
        .addToSchema(Schema.create(Schema.Type.STRING));

    Assert.assertEquals("Should use DATE_CONVERSION for date type",
        "DATE_CONVERSION", compiler.conversionInstance(dateSchema));
    Assert.assertEquals("Should use TIME_CONVERSION for time type",
        "TIME_CONVERSION", compiler.conversionInstance(timeSchema));
    Assert.assertEquals("Should use TIMESTAMP_CONVERSION for date type",
        "TIMESTAMP_CONVERSION", compiler.conversionInstance(timestampSchema));
    Assert.assertEquals("Should use null for decimal if the flag is off",
        "DECIMAL_CONVERSION", compiler.conversionInstance(decimalSchema));
    Assert.assertEquals("Should use null for decimal if the flag is off",
        "null", compiler.conversionInstance(uuidSchema));
  }

}