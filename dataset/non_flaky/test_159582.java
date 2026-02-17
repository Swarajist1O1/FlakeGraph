class DummyClass_159582 {
    @Test
    public void tableToString() {
        Table t = new Table();

        t.addColumn(RealmFieldType.STRING, "stringCol");
        t.addColumn(RealmFieldType.INTEGER, "intCol");
        t.addColumn(RealmFieldType.BOOLEAN, "boolCol");

        t.add("s1", 1, true);
        t.add("s2", 2, false);

        String expected = "The Table contains 3 columns: stringCol, intCol, boolCol. And 2 rows.";
        assertEquals(expected, t.toString());
    }

}