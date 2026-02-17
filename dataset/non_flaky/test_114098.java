class DummyClass_114098 {
    @Test
    public void fromImmutable_constructsImmutableTableSchema() {
        ImmutableTableSchema<SimpleImmutable> immutableTableSchema =
            TableSchema.fromImmutableClass(SimpleImmutable.class);

        assertThat(immutableTableSchema).isNotNull();
    }

}