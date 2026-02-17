class DummyClass_159580 {
    @Test
    public void testWhenColumnNameIsExactly63CharLong() {

        Table table = new Table();
        table.addColumn(RealmFieldType.STRING, "THIS STRING HAS 63 CHARACTERS PERFECT FOR THE MAX 63 CHARACTERS");
    }

}