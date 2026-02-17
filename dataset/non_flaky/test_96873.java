class DummyClass_96873 {
  @Test
  public void testJavaTypeWithJsr310DateTimeTypes() throws Exception {
    SpecificCompiler compiler = createCompiler(JSR310);

    Schema dateSchema = LogicalTypes.date()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timeSchema = LogicalTypes.timeMillis()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timestampSchema = LogicalTypes.timestampMillis()
        .addToSchema(Schema.create(Schema.Type.LONG));

    // Date/time types should always use upper level java classes
    Assert.assertEquals("Should use java.time.LocalDate for date type",
        "java.time.LocalDate", compiler.javaType(dateSchema));
    Assert.assertEquals("Should use java.time.LocalTime for time-millis type",
        "java.time.LocalTime", compiler.javaType(timeSchema));
    Assert.assertEquals("Should use java.time.Instant for timestamp-millis type",
        "java.time.Instant", compiler.javaType(timestampSchema));
  }

}