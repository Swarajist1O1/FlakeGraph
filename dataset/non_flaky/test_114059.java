class DummyClass_114059 {
    @Test
    public void equalityIsBasedOnInnerEquality() {
        verifyEquals(EnhancedType.of(String.class), EnhancedType.of(String.class));
        verifyNotEquals(EnhancedType.of(String.class), EnhancedType.of(Integer.class));

        verifyEquals(new EnhancedType<Map<String, List<String>>>(){}, new EnhancedType<Map<String, List<String>>>(){});
        verifyNotEquals(new EnhancedType<Map<String, List<String>>>(){}, new EnhancedType<Map<String,
            List<Integer>>>(){});

        TableSchema<String> tableSchema = StaticTableSchema.builder(String.class).build();

        verifyNotEquals(EnhancedType.documentOf(String.class,
                                             tableSchema,
                                             b -> b.ignoreNulls(false)), EnhancedType.documentOf(String.class,
                                                                                                 tableSchema,
                                                                                                 b -> b.ignoreNulls(true)));
        verifyEquals(EnhancedType.documentOf(String.class,
                                                tableSchema,
                                                b -> b.ignoreNulls(false).preserveEmptyObject(true)),
                        EnhancedType.documentOf(String.class,
                                                tableSchema,
                                                b -> b.ignoreNulls(false).preserveEmptyObject(true)));
    }

}