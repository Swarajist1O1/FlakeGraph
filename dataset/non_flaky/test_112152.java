class DummyClass_112152 {
    @Test
    public void assertToItemsString() {
        assertThat(ShardingItems.toItemsString(Arrays.asList(0, 1, 2)), is("0,1,2"));
    }

}