class DummyClass_38240 {
    @Test
    public void testGetLatestTimestamps() {
        putTestDataForMultipleTimestamps();
        Map<Cell, Long> timestamps = keyValueService.getLatestTimestamps(TEST_TABLE,
                ImmutableMap.of(Cell.create(row0, column0), TEST_TIMESTAMP + 2));
        assertTrue("Incorrect number of values returned.", timestamps.size() == 1);
        assertEquals("Incorrect value returned.", new Long(TEST_TIMESTAMP + 1),
                timestamps.get(Cell.create(row0, column0)));
    }

}