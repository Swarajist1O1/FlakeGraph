class DummyClass_38205 {
    @Test
    public void testReadBeforeTime() {
        final Cell cell = Cell.create("r1".getBytes(), COMMIT_TS_COLUMN);
        db.put(TABLE, ImmutableMap.of(cell, "v1".getBytes()), 2);
        final Map<Cell, Value> res = db.get(TABLE, ImmutableMap.of(cell, 2L));
        assertTrue(res.isEmpty());
    }

}