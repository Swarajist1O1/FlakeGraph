class DummyClass_114091 {
    @Test
    public void numericKeys_convertsToCorrectAttributeValue() {
        Key key = Key.builder().partitionValue(123).sortValue(45.6).build();

        assertThat(key.partitionKeyValue(), is(AttributeValue.builder().n("123").build()));
        assertThat(key.sortKeyValue(), is(Optional.of(AttributeValue.builder().n("45.6").build())));
    }

}