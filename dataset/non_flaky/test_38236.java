class DummyClass_38236 {
    @Test
    public void testGetRowsWhenMultipleVersions() {
        putTestDataForMultipleTimestamps();
        Map<Cell, Value> result = keyValueService.getRows(
                TEST_TABLE,
                ImmutableSet.of(row0),
                ColumnSelection.all(),
                TEST_TIMESTAMP + 1);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(Cell.create(row0, column0)));
        assertTrue(result.containsValue(Value.create(value0_t0, TEST_TIMESTAMP)));

        result = keyValueService.getRows(
                TEST_TABLE,
                ImmutableSet.of(row0),
                ColumnSelection.all(),
                TEST_TIMESTAMP + 2);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(Cell.create(row0, column0)));
        assertTrue(result.containsValue(Value.create(value0_t1, TEST_TIMESTAMP + 1)));
    }

}