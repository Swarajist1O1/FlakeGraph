class DummyClass_38202 {
    @Test
    public void testReadGood2() {
        final Cell cell = Cell.create("r1".getBytes(), "2".getBytes());
        final Cell cell2 = Cell.create("r".getBytes(), "12".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 1000);
        db.put(TABLE, ImmutableMap.of(cell2, "v2".getBytes()), 1000);
        final Map<Cell, Value> res = db.get(TABLE, ImmutableMap.of(cell, 1001L));
        final Value value = res.get(cell);
        assertEquals(1000, value.getTimestamp());
        assertEquals("v1", new String(value.getContents()));
    }

}