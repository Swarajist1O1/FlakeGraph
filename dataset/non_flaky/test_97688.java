class DummyClass_97688 {
    @Test
    public void testCustomOptional() throws Exception {
        final Settings settings = TestUtils.settings();
        settings.mapDate = DateMapping.asString;
        settings.customTypeProcessor = new TypeProcessor() {
            @Override
            public TypeProcessor.Result processType(Type javaType, TypeProcessor.Context context) {
                final Type[] typeArguments = tryGetParameterizedTypeArguments(javaType, CustomOptional.class);
                if (typeArguments != null) {
                    final TypeProcessor.Result result = context.processType(typeArguments[0]);
                    return new Result(result.getTsType().optional(), result.getDiscoveredClasses());
                }
                return null;
            }

}