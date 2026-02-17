class DummyClass_97756 {
    @Test
    public void testTypeConversion() {
        TypeProcessor converter = new DefaultTypeProcessor();
        final TypeProcessor.Context context = getTestContext(converter);
        assertEquals(context.getSymbol(A.class).getFullName(), converter.processType(A.class, context).getTsType().toString());
        assertEquals(context.getSymbol(B.class).getFullName(), converter.processType(B.class, context).getTsType().toString());
        assertEquals(TsType.Void, converter.processType(void.class, context).getTsType());
        assertEquals(TsType.Number, converter.processType(BigDecimal.class, context).getTsType());
        assertEquals(TsType.String, converter.processType(UUID.class, context).getTsType());
        assertEquals(TsType.Number.optional(), converter.processType(OptionalInt.class, context).getTsType());
        assertEquals(TsType.Number.optional(), converter.processType(OptionalLong.class, context).getTsType());
        assertEquals(TsType.Number.optional(), converter.processType(OptionalDouble.class, context).getTsType());
    }

}