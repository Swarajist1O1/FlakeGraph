class DummyClass_96871 {
  @Test
  public void testJavaTypeWithDecimalLogicalTypeEnabled() throws Exception {
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

    // Date/time types should always use upper level java classes
    // Decimal type target class depends on configuration
    // UUID should always be CharSequence since we haven't added its
    // support in SpecificRecord
    Assert.assertEquals("Should use Joda LocalDate for date type",
        "org.joda.time.LocalDate", compiler.javaType(dateSchema));
    Assert.assertEquals("Should use Joda LocalTime for time-millis type",
        "org.joda.time.LocalTime", compiler.javaType(timeSchema));
    Assert.assertEquals("Should use Joda DateTime for timestamp-millis type",
        "org.joda.time.DateTime", compiler.javaType(timestampSchema));
    Assert.assertEquals("Should use Java BigDecimal type",
        "java.math.BigDecimal", compiler.javaType(decimalSchema));
    Assert.assertEquals("Should use Java CharSequence type",
        "java.lang.CharSequence", compiler.javaType(uuidSchema));
  }

}