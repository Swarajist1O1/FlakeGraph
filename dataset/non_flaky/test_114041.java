class DummyClass_114041 {
    @Test
    public void putItem_returnItemCollectionMetrics_set_itemCollectionMetricsNotNull() {
        Record record = new Record().setId(1).setId2(10);
        UpdateItemEnhancedRequest<Record> request = UpdateItemEnhancedRequest.builder(Record.class)
                                                                             .item(record)
                                                                             .returnItemCollectionMetrics(ReturnItemCollectionMetrics.SIZE)
                                                                             .build();

        UpdateItemEnhancedResponse<Record> response = mappedTable.updateItemWithResponse(request);

        assertThat(response.itemCollectionMetrics()).isNotNull();
    }

}