class DummyClass_38211 {
    @Test
    public void testGetRangeCellOverlap2() {
        final Cell cell = Cell.create("1".getBytes(), "1".getBytes());
        final Cell cell2 = Cell.create("12".getBytes(), "0".getBytes());
        final Cell cell3 = Cell.create("1".getBytes(), "3".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 2);
        db.put(TABLE, ImmutableMap.of(cell2, "v2".getBytes()), 2);
        db.put(TABLE, ImmutableMap.of(cell3, "v3".getBytes()), 2);
        final ClosableIterator<? extends RowResult<Value>> it = db.getRange(TABLE, RangeRequest.builder().build(), 3);
        try {
            assertEquals(2, Iterators.size(it));
        } finally {
            it.close();
        }
    }

}