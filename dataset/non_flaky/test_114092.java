class DummyClass_114092 {
    @Test
    public void stringKeys_convertsToCorrectAttributeValue() {
        Key key = Key.builder().partitionValue("one").sortValue("two").build();

        assertThat(key.partitionKeyValue(), is(AttributeValue.builder().s("one").build()));
        assertThat(key.sortKeyValue(), is(Optional.of(AttributeValue.builder().s("two").build())));
    }

}