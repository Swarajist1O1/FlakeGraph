class DummyClass_38247 {
    @Test
    public void testDeleteMultipleVersions() {
        putTestDataForMultipleTimestamps();
        Cell cell = Cell.create(row0, column0);
        ClosableIterator<RowResult<Value>> result = keyValueService.getRange(
                TEST_TABLE,
                RangeRequest.all(),
                TEST_TIMESTAMP + 1);
        assertTrue(result.hasNext());

        keyValueService.delete(TEST_TABLE, ImmutableMultimap.of(cell, TEST_TIMESTAMP));

        result = keyValueService.getRange(TEST_TABLE, RangeRequest.all(), TEST_TIMESTAMP + 1);
        assertTrue(!result.hasNext());

        result = keyValueService.getRange(TEST_TABLE, RangeRequest.all(), TEST_TIMESTAMP + 2);
        assertTrue(result.hasNext());
    }

}