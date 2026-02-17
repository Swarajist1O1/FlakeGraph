class DummyClass_114100 {
    @Test
    public void fromClass_constructsImmutableTableSchema() {
        TableSchema<SimpleImmutable> tableSchema = TableSchema.fromClass(SimpleImmutable.class);
        assertThat(tableSchema).isInstanceOf(ImmutableTableSchema.class);
    }

}