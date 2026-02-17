class DummyClass_112151 {
    @Test
    public void assertToItemsStringWhenEmpty() {
        assertThat(ShardingItems.toItemsString(Collections.<Integer>emptyList()), is(""));
    }

}