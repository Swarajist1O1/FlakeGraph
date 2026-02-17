class DummyClass_114040 {
    @Test
    public void updateItem_returnItemCollectionMetrics_set_itemCollectionMetricsNull() {
        Record record = new Record().setId(1).setId2(10);
        UpdateItemEnhancedRequest<Record> request = UpdateItemEnhancedRequest.builder(Record.class)
                                                                          .item(record)
                                                                          .build();

        UpdateItemEnhancedResponse<Record> response = mappedTable.updateItemWithResponse(request);

        assertThat(response.itemCollectionMetrics()).isNull();
    }

}