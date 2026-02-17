class DummyClass_38199 {
    @Test
    public void testCreate() {
        TableReference otherTable = TableReference.createWithEmptyNamespace("yodog");
        db.createTable(TABLE, AtlasDbConstants.EMPTY_TABLE_METADATA);
        db.createTable(otherTable, AtlasDbConstants.EMPTY_TABLE_METADATA);
        db.createTable(TRANSACTION_TABLE, AtlasDbConstants.EMPTY_TABLE_METADATA);
        assertEquals(ImmutableSet.of(TABLE, otherTable, TRANSACTION_TABLE),
                db.getAllTableNames());
    }

}