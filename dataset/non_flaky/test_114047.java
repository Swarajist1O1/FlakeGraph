class DummyClass_114047 {
    @Test
    public void deleteItem_returnConsumedCapacity_set_consumedCapacityNotNull() {
        Key key = Key.builder().partitionValue(1).sortValue(10).build();

        DeleteItemEnhancedResponse<Record> response =
            mappedTable.deleteItemWithResponse(r -> r.key(key).returnConsumedCapacity(ReturnConsumedCapacity.TOTAL));

        assertThat(response.consumedCapacity()).isNotNull();
    }

}