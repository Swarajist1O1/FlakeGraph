class DummyClass_38237 {
    @Test
    public void testGetRowsWhenMultipleVersionsAndColumnsSelected() {
        putTestDataForMultipleTimestamps();
        Map<Cell, Value> result = keyValueService.getRows(
                TEST_TABLE,
                ImmutableSet.of(row0),
                ColumnSelection.create(ImmutableSet.of(column0)),
                TEST_TIMESTAMP + 1);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(Cell.create(row0, column0)));
        assertTrue(result.containsValue(Value.create(value0_t0, TEST_TIMESTAMP)));

        result = keyValueService.getRows(
                TEST_TABLE,
                ImmutableSet.of(row0),
                ColumnSelection.create(ImmutableSet.of(column0)),
                TEST_TIMESTAMP + 2);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(Cell.create(row0, column0)));
        assertTrue(result.containsValue(Value.create(value0_t1, TEST_TIMESTAMP + 1)));
    }

}