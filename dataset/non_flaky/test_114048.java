class DummyClass_114048 {
    @Test
    public void delete_returnItemCollectionMetrics_set_itemCollectionMetricsNotNull() {
        Key key = Key.builder().partitionValue(1).sortValue(10).build();

        DeleteItemEnhancedResponse<Record> response =
            mappedTable.deleteItemWithResponse(r -> r.key(key).returnItemCollectionMetrics(ReturnItemCollectionMetrics.SIZE));

        assertThat(response.itemCollectionMetrics()).isNotNull();
    }

}