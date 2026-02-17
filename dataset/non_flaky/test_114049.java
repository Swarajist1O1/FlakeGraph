class DummyClass_114049 {
    @Test
    public void deleteItem_returnConsumedCapacity_unset_consumedCapacityNull() {
        Key key = Key.builder().partitionValue(1).sortValue(10).build();

        DeleteItemEnhancedResponse<Record> response = mappedTable.deleteItemWithResponse(r -> r.key(key)).join();

        assertThat(response.consumedCapacity()).isNull();
    }

}