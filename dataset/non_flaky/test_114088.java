class DummyClass_114088 {
    @Test
    public void getPrimaryKeyMap_partitionOnly() {
        Map<String, AttributeValue> expectedResult = new HashMap<>();
        expectedResult.put("id", AttributeValue.builder().s("id123").build());
        assertThat(partitionOnlyKey.primaryKeyMap(FakeItemWithIndices.getTableSchema()), is(expectedResult));
    }

}