class DummyClass_114090 {
    @Test
    public void getSortKeyValue_partitionOnly() {
        assertThat(partitionOnlyKey.sortKeyValue(), is(Optional.empty()));
    }

}