class DummyClass_97687 {
    @Test
    public void testCustomTypeConversion() {
        final Settings settings = TestUtils.settings();
        // suppose we want to override how A is parsed
        settings.customTypeProcessor = new TypeProcessor() {
            @Override
            public TypeProcessor.Result processType(Type javaType, TypeProcessor.Context context) {
                if (javaType.equals(B.class)) {
                    return new Result(TsType.Number.optional());
                }
                return null;
            }

}