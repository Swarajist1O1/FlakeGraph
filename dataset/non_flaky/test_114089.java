class DummyClass_114089 {
    @Test
    public void getPartitionKeyValue_partitionOnly() {
        assertThat(partitionOnlyKey.partitionKeyValue(),
                   is(AttributeValue.builder().s("id123").build()));
    }

}