class DummyClass_114093 {
    @Test
    public void binaryKeys_convertsToCorrectAttributeValue() {
        SdkBytes partition = SdkBytes.fromString("one", StandardCharsets.UTF_8);
        SdkBytes sort = SdkBytes.fromString("two", StandardCharsets.UTF_8);

        Key key = Key.builder().partitionValue(partition).sortValue(sort).build();

        assertThat(key.partitionKeyValue(), is(AttributeValue.builder().b(partition).build()));
        assertThat(key.sortKeyValue(), is(Optional.of(AttributeValue.builder().b(sort).build())));
    }

}