class DummyClass_112148 {
    @Test
    public void assertToItemListWhenEmpty() {
        assertThat(ShardingItems.toItemList(""), is(Collections.EMPTY_LIST));
    }

}