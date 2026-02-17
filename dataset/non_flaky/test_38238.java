class DummyClass_38238 {
    @Test
    public void testGetWhenMultipleVersions() {
        putTestDataForMultipleTimestamps();
        Cell cell = Cell.create(row0, column0);
        Value val0 = Value.create(value0_t0, TEST_TIMESTAMP);
        Value val1 = Value.create(value0_t1, TEST_TIMESTAMP + 1);

        assertTrue(keyValueService.get(TEST_TABLE, ImmutableMap.of(cell, TEST_TIMESTAMP)).isEmpty());

        Map<Cell, Value> result = keyValueService.get(
                TEST_TABLE,
                ImmutableMap.of(cell, TEST_TIMESTAMP + 1));
        assertTrue(result.containsKey(cell));
        assertEquals(1, result.size());
        assertTrue(result.containsValue(val0));

        result = keyValueService.get(TEST_TABLE, ImmutableMap.of(cell, TEST_TIMESTAMP + 2));

        assertEquals(1, result.size());
        assertTrue(result.containsKey(cell));
        assertTrue(result.containsValue(val1));

        result = keyValueService.get(TEST_TABLE, ImmutableMap.of(cell, TEST_TIMESTAMP + 3));

        assertEquals(1, result.size());
        assertTrue(result.containsKey(cell));
        assertTrue(result.containsValue(val1));
    }

}