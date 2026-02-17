class DummyClass_38252 {
    @Test
    public void testAddGCSentinelValues() {
        putTestDataForMultipleTimestamps();
        Cell cell = Cell.create(row0, column0);

        Multimap<Cell, Long> timestampsBefore = keyValueService.getAllTimestamps(TEST_TABLE, ImmutableSet.of(cell), Long.MAX_VALUE);
        assertEquals(2, timestampsBefore.size());
        assertTrue(!timestampsBefore.containsEntry(cell, Value.INVALID_VALUE_TIMESTAMP));

        keyValueService.addGarbageCollectionSentinelValues(TEST_TABLE, ImmutableSet.of(cell));

        Multimap<Cell, Long> timestampsAfter1 = keyValueService.getAllTimestamps(TEST_TABLE, ImmutableSet.of(cell), Long.MAX_VALUE);
        assertEquals(3, timestampsAfter1.size());
        assertTrue(timestampsAfter1.containsEntry(cell, Value.INVALID_VALUE_TIMESTAMP));

        keyValueService.addGarbageCollectionSentinelValues(TEST_TABLE, ImmutableSet.of(cell));

        Multimap<Cell, Long> timestampsAfter2 = keyValueService.getAllTimestamps(TEST_TABLE, ImmutableSet.of(cell), Long.MAX_VALUE);
        assertEquals(3, timestampsAfter2.size());
        assertTrue(timestampsAfter2.containsEntry(cell, Value.INVALID_VALUE_TIMESTAMP));
    }

}