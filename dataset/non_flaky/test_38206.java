class DummyClass_38206 {
    @Test
    public void testGetRow() {
        final Cell cell = Cell.create("r1".getBytes(), "c1".getBytes());
        final Cell cell2 = Cell.create("r1".getBytes(), "c2".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 2);
        db.put(TABLE, ImmutableMap.of(cell2, "v2".getBytes()), 2);
        final Map<Cell, Value> rows = db.getRows(TABLE, ImmutableList.of("r1".getBytes()), ColumnSelection.all(), 3);
        assertEquals(2, rows.size());
    }

}