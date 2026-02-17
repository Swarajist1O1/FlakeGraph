class DummyClass_114075 {
    @Test
    public void documentOf_withEnhancedTypeConfiguration() {
        TableSchema<String> tableSchema = StaticTableSchema.builder(String.class).build();
        EnhancedType<String> type = EnhancedType.documentOf(String.class, tableSchema, b -> b.preserveEmptyObject(true));
        assertThat(type.documentConfiguration()).isPresent();
        assertThat(type.documentConfiguration().get().preserveEmptyObject()).isTrue();
    }

}