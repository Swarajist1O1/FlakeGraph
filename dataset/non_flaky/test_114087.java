class DummyClass_114087 {
    @Test
    public void getKeyMap_partitionOnly() {
        Map<String, AttributeValue> expectedResult = new HashMap<>();
        expectedResult.put("gsi_id", AttributeValue.builder().s("id123").build());
        assertThat(partitionOnlyKey.keyMap(FakeItemWithIndices.getTableSchema(), "gsi_1"), is(expectedResult));
    }

}