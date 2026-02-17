class DummyClass_38241 {
    @Test
    public void testGetWithMultipleVersions() {
        putTestDataForMultipleTimestamps();
        Map<Cell, Value> values = keyValueService.get(TEST_TABLE,
                ImmutableMap.of(Cell.create(row0, column0), TEST_TIMESTAMP + 2));
        assertTrue("Incorrect number of values returned.", values.size() == 1);
        assertEquals("Incorrect value returned.", Value.create(value0_t1, TEST_TIMESTAMP + 1),
                values.get(Cell.create(row0, column0)));
    }

}