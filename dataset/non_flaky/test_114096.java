class DummyClass_114096 {
    @Test
    public void builder_constructsStaticTableSchemaBuilder() {
        StaticTableSchema.Builder<FakeItem> builder = TableSchema.builder(FakeItem.class);
        assertThat(builder).isNotNull();
    }

}