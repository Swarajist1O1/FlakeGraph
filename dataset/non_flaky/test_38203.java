class DummyClass_38203 {
    @Test
    public void testReadGood3() {
        final Cell cell = Cell.create("r1".getBytes(), COMMIT_TS_COLUMN);
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), Long.MAX_VALUE - 3);
        final Map<Cell, Value> res = db.get(TABLE, ImmutableMap.of(cell, Long.MAX_VALUE - 2));
        final Value value = res.get(cell);
        assertEquals(Long.MAX_VALUE - 3, value.getTimestamp());
        assertEquals("v1", new String(value.getContents()));
    }

}