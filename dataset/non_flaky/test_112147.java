class DummyClass_112147 {
    @Test
    public void assertTtoItemListWhenNull() {
        assertThat(ShardingItems.toItemList(null), is(Collections.EMPTY_LIST));
    }

}