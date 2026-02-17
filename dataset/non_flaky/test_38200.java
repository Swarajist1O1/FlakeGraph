class DummyClass_38200 {
    @Test
    public void testReadNoExist() {
        final Cell cell = Cell.create("r1".getBytes(), COMMIT_TS_COLUMN);
        final Map<Cell, Value> res = db.get(TABLE, ImmutableMap.of(cell, 1L));
        assertTrue(res.isEmpty());
    }

}