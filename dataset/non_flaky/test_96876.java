class DummyClass_96876 {
  @Test
  public void testNullableTypesJavaUnbox() throws Exception {
    SpecificCompiler compiler = createCompiler();
    compiler.setEnableDecimalLogicalType(false);

    // Nullable types should return boxed types instead of primitive types
    Schema nullableIntSchema1 = Schema.createUnion(
        Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.INT));
    Schema nullableIntSchema2 = Schema.createUnion(
        Schema.create(Schema.Type.INT), Schema.create(Schema.Type.NULL));
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableIntSchema1), "java.lang.Integer");
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableIntSchema2), "java.lang.Integer");

    Schema nullableLongSchema1 = Schema.createUnion(
        Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.LONG));
    Schema nullableLongSchema2 = Schema.createUnion(
        Schema.create(Schema.Type.LONG), Schema.create(Schema.Type.NULL));
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableLongSchema1), "java.lang.Long");
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableLongSchema2), "java.lang.Long");

    Schema nullableFloatSchema1 = Schema.createUnion(
        Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.FLOAT));
    Schema nullableFloatSchema2 = Schema.createUnion(
        Schema.create(Schema.Type.FLOAT), Schema.create(Schema.Type.NULL));
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableFloatSchema1), "java.lang.Float");
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableFloatSchema2), "java.lang.Float");

    Schema nullableDoubleSchema1 = Schema.createUnion(
        Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.DOUBLE));
    Schema nullableDoubleSchema2 = Schema.createUnion(
        Schema.create(Schema.Type.DOUBLE), Schema.create(Schema.Type.NULL));
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableDoubleSchema1), "java.lang.Double");
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableDoubleSchema2), "java.lang.Double");

    Schema nullableBooleanSchema1 = Schema.createUnion(
        Schema.create(Schema.Type.NULL), Schema.create(Schema.Type.BOOLEAN));
    Schema nullableBooleanSchema2 = Schema.createUnion(
        Schema.create(Schema.Type.BOOLEAN), Schema.create(Schema.Type.NULL));
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableBooleanSchema1), "java.lang.Boolean");
    Assert.assertEquals("Should return boxed type",
        compiler.javaUnbox(nullableBooleanSchema2), "java.lang.Boolean");
  }

}