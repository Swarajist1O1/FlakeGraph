class DummyClass_96875 {
  @Test
  public void testJavaUnboxJsr310DateTime() throws Exception {
    SpecificCompiler compiler = createCompiler(JSR310);

    Schema dateSchema = LogicalTypes.date()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timeSchema = LogicalTypes.timeMillis()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timestampSchema = LogicalTypes.timestampMillis()
        .addToSchema(Schema.create(Schema.Type.LONG));
    // Date/time types should always use upper level java classes, even though
    // their underlying representations are primitive types
    Assert.assertEquals("Should use java.time.LocalDate for date type",
        "java.time.LocalDate", compiler.javaUnbox(dateSchema));
    Assert.assertEquals("Should use java.time.LocalTime for time-millis type",
        "java.time.LocalTime", compiler.javaUnbox(timeSchema));
    Assert.assertEquals("Should use java.time.Instant for timestamp-millis type",
        "java.time.Instant", compiler.javaUnbox(timestampSchema));
  }

}