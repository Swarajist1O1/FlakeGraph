class DummyClass_38248 {
    @Test
    public void testPutWithTimestamps() {
        putTestDataForMultipleTimestamps();
        final Cell cell = Cell.create(row0, column0);
        final Value val1 = Value.create(value0_t1, TEST_TIMESTAMP + 1);
        final Value val5 = Value.create(value0_t5, TEST_TIMESTAMP + 5);
        keyValueService.putWithTimestamps(TEST_TABLE, ImmutableMultimap.of(cell, val5));
        assertEquals(
                val5,
                keyValueService.get(TEST_TABLE, ImmutableMap.of(cell, TEST_TIMESTAMP + 6)).get(cell));
        assertEquals(
                val1,
                keyValueService.get(TEST_TABLE, ImmutableMap.of(cell, TEST_TIMESTAMP + 5)).get(cell));
        keyValueService.delete(TEST_TABLE, ImmutableMultimap.of(cell, TEST_TIMESTAMP + 5));
    }

}