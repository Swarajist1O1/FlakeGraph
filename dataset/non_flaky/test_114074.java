class DummyClass_114074 {
    @Test
    public void documentOf_toString_doesNotRaiseNPE() {
        TableSchema<String> tableSchema = StaticTableSchema.builder(String.class).build();
        EnhancedType<String> type = EnhancedType.documentOf(String.class, tableSchema);
        assertThatCode(() -> type.toString()).doesNotThrowAnyException();
    }

}