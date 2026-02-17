class DummyClass_38204 {
    @Test
    public void testReadGood4() {
        final Cell cell = Cell.create("r,1".getBytes(), ",c,1,".getBytes());
        db.put(TABLE, ImmutableMap.of(cell, "v,1".getBytes()), 1);
        final Map<Cell, Value> res = db.get(TABLE, ImmutableMap.of(cell, 2L));
        final Value value = res.get(cell);
        assertEquals(1, value.getTimestamp());
        assertEquals("v,1", new String(value.getContents()));
    }

}