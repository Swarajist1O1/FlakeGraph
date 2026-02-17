class DummyClass_38210 {
    @Test
    public void testGetRangeCellOverlap() {
        final Cell cell = Cell.create("12".getBytes(), "34".getBytes());
        final Cell cell2 = Cell.create("1".getBytes(), "235".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 2);
        db.put(TABLE, ImmutableMap.of(cell2, "v2".getBytes()), 2);
        ClosableIterator<? extends RowResult<Value>> it = db.getRange(TABLE, RangeRequest.builder().build(), 3);
        try {
            assertEquals(2, Iterators.size(it));
        } finally {
            it.close();
        }
        it = db.getRange(TABLE, RangeRequest.builder().endRowExclusive("12".getBytes()).build(), 3);
        try {
            assertEquals(1, Iterators.size(it));
        } finally {
            it.close();
        }
        it = db.getRange(TABLE, RangeRequest.builder().startRowInclusive("12".getBytes()).build(), 3);
        try {
            assertEquals(1, Iterators.size(it));
        } finally {
            it.close();
        }
    }

}