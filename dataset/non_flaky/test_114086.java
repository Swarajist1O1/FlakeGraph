class DummyClass_114086 {
    @Test
    public void getSortKeyValue() {
        assertThat(key.sortKeyValue(), is(Optional.of(AttributeValue.builder().s("id456").build())));
    }

}