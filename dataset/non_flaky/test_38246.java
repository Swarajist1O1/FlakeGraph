class DummyClass_38246 {
    @Test
    public void testDelete() {
        putTestDataForSingleTimestamp();
        assertEquals(3, Iterators.size(keyValueService.getRange(
                TEST_TABLE,
                RangeRequest.all(),
                TEST_TIMESTAMP + 1)));
        keyValueService.delete(
                TEST_TABLE,
                ImmutableMultimap.of(Cell.create(row0, column0), TEST_TIMESTAMP));
        assertEquals(3, Iterators.size(keyValueService.getRange(
                TEST_TABLE,
                RangeRequest.all(),
                TEST_TIMESTAMP + 1)));
        keyValueService.delete(
                TEST_TABLE,
                ImmutableMultimap.of(Cell.create(row0, column1), TEST_TIMESTAMP));
        assertEquals(2, Iterators.size(keyValueService.getRange(
                TEST_TABLE,
                RangeRequest.all(),
                TEST_TIMESTAMP + 1)));
        keyValueService.delete(
                TEST_TABLE,
                ImmutableMultimap.of(Cell.create(row1, column0), TEST_TIMESTAMP));
        assertEquals(2, Iterators.size(keyValueService.getRange(
                TEST_TABLE,
                RangeRequest.all(),
                TEST_TIMESTAMP + 1)));
        keyValueService.delete(
                TEST_TABLE,
                ImmutableMultimap.of(Cell.create(row1, column2), TEST_TIMESTAMP));
        assertEquals(1, Iterators.size(keyValueService.getRange(
                TEST_TABLE,
                RangeRequest.all(),
                TEST_TIMESTAMP + 1)));
    }

}