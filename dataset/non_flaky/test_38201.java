class DummyClass_38201 {
    @Test
    public void testReadGood() {
        final Cell cell = Cell.create("r1".getBytes(), "2".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 1);
        final Map<Cell, Value> res = db.get(TABLE, ImmutableMap.of(cell, 2L));
        assertEquals(1, res.size());
        final Value value = res.get(cell);
        assertEquals(1, value.getTimestamp());
        assertEquals("v1", new String(value.getContents()));
    }

}