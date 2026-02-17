class DummyClass_114043 {
    @Test
    public void putItem_returnItemCollectionMetrics_set_itemCollectionMetricsNotNull() {
        Record record = new Record().setId(1).setId2(10);
        PutItemEnhancedRequest<Record> request = PutItemEnhancedRequest.builder(Record.class)
                                                                       .item(record)
                                                                       .returnItemCollectionMetrics(ReturnItemCollectionMetrics.SIZE)
                                                                       .build();

        PutItemEnhancedResponse<Record> response = mappedTable.putItemWithResponse(request);

        assertThat(response.itemCollectionMetrics()).isNotNull();
    }

}