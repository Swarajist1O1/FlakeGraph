class DummyClass_96874 {
  @Test
  public void testJavaUnbox() throws Exception {
    SpecificCompiler compiler = createCompiler();
    compiler.setEnableDecimalLogicalType(false);

    Schema intSchema = Schema.create(Schema.Type.INT);
    Schema longSchema = Schema.create(Schema.Type.LONG);
    Schema floatSchema = Schema.create(Schema.Type.FLOAT);
    Schema doubleSchema = Schema.create(Schema.Type.DOUBLE);
    Schema boolSchema = Schema.create(Schema.Type.BOOLEAN);
    Assert.assertEquals("Should use int for Type.INT",
        "int", compiler.javaUnbox(intSchema));
    Assert.assertEquals("Should use long for Type.LONG",
        "long", compiler.javaUnbox(longSchema));
    Assert.assertEquals("Should use float for Type.FLOAT",
        "float", compiler.javaUnbox(floatSchema));
    Assert.assertEquals("Should use double for Type.DOUBLE",
        "double", compiler.javaUnbox(doubleSchema));
    Assert.assertEquals("Should use boolean for Type.BOOLEAN",
        "boolean", compiler.javaUnbox(boolSchema));

    Schema dateSchema = LogicalTypes.date()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timeSchema = LogicalTypes.timeMillis()
        .addToSchema(Schema.create(Schema.Type.INT));
    Schema timestampSchema = LogicalTypes.timestampMillis()
        .addToSchema(Schema.create(Schema.Type.LONG));
    // Date/time types should always use upper level java classes, even though
    // their underlying representations are primitive types
    Assert.assertEquals("Should use Joda LocalDate for date type",
        "org.joda.time.LocalDate", compiler.javaUnbox(dateSchema));
    Assert.assertEquals("Should use Joda LocalTime for time-millis type",
        "org.joda.time.LocalTime", compiler.javaUnbox(timeSchema));
    Assert.assertEquals("Should use Joda DateTime for timestamp-millis type",
        "org.joda.time.DateTime", compiler.javaUnbox(timestampSchema));
  }

}