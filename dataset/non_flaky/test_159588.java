class DummyClass_159588 {
    @Test
    public void getNonExistingColumn() {
        Table t = new Table();
        t.addColumn(RealmFieldType.INTEGER, "int");

        assertEquals(-1, t.getColumnIndex("non-existing column"));
        try { t.getColumnIndex(null); fail("column name null"); } catch (IllegalArgumentException ignored) { }
    }

}