class DummyClass_114052 {
    @Test
    public void putItem_returnItemCollectionMetrics_set_itemCollectionMetricsNull() {
        Record record = new Record().setId(1).setId2(10);
        PutItemEnhancedRequest<Record> request = PutItemEnhancedRequest.builder(Record.class)
                                                                       .item(record)
                                                                       .build();

        PutItemEnhancedResponse<Record> response = mappedTable.putItemWithResponse(request).join();

        assertThat(response.itemCollectionMetrics()).isNull();
    }

}