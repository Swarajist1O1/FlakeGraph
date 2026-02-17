class DummyClass_114042 {
    @Test
    public void putItem_returnItemCollectionMetrics_set_itemCollectionMetricsNull() {
        Record record = new Record().setId(1).setId2(10);
        PutItemEnhancedRequest<Record> request = PutItemEnhancedRequest.builder(Record.class)
                                                                       .item(record)
                                                                       .build();

        PutItemEnhancedResponse<Record> response = mappedTable.putItemWithResponse(request);

        assertThat(response.itemCollectionMetrics()).isNull();
    }

}