class DummyClass_38212 {
    @Test
    public void testDoubleWriteToTransactionTable() {
        db.createTable(TRANSACTION_TABLE, AtlasDbConstants.EMPTY_TABLE_METADATA);
        final Cell cell = Cell.create("r1".getBytes(), COMMIT_TS_COLUMN);
        db.putUnlessExists(TRANSACTION_TABLE, ImmutableMap.of(cell, "v1".getBytes()));
        try {
            db.putUnlessExists(TRANSACTION_TABLE, ImmutableMap.of(cell, "v2".getBytes()));
            fail();
        } catch (KeyAlreadyExistsException e) {
            // expected
        }
        final Map<Cell, Value> res = db.get(TRANSACTION_TABLE, ImmutableMap.of(cell, 1L));
        final Value value = res.get(cell);
        assertEquals(0L, value.getTimestamp());
        assertEquals("v1", new String(value.getContents()));
    }

}