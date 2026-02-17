class DummyClass_38245 {
    @Test
    public void testGetAllTimestamps() {
        putTestDataForMultipleTimestamps();
        final Cell cell = Cell.create(row0, column0);
        final Set<Cell> cellSet = ImmutableSet.of(cell);
        Multimap<Cell, Long> timestamps = keyValueService.getAllTimestamps(
                TEST_TABLE,
                cellSet,
                TEST_TIMESTAMP);
        assertEquals(0, timestamps.size());

        timestamps = keyValueService.getAllTimestamps(TEST_TABLE, cellSet, TEST_TIMESTAMP + 1);
        assertEquals(1, timestamps.size());
        assertTrue(timestamps.containsEntry(cell, TEST_TIMESTAMP));

        timestamps = keyValueService.getAllTimestamps(TEST_TABLE, cellSet, TEST_TIMESTAMP + 2);
        assertEquals(2, timestamps.size());
        assertTrue(timestamps.containsEntry(cell, TEST_TIMESTAMP));
        assertTrue(timestamps.containsEntry(cell, TEST_TIMESTAMP + 1));

        assertEquals(
                timestamps,
                keyValueService.getAllTimestamps(TEST_TABLE, cellSet, TEST_TIMESTAMP + 3));
    }

}