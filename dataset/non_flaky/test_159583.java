class DummyClass_159583 {
    @Test
    public void rowOperationsOnZeroRow(){

        Table t = new Table();
        // Removes rows without columns.
        try { t.moveLastOver(0);  fail("No rows in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}
        try { t.moveLastOver(10); fail("No rows in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}

        // Column added, remove rows again.
        t.addColumn(RealmFieldType.STRING, "");
        try { t.moveLastOver(0);  fail("No rows in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}
        try { t.moveLastOver(10); fail("No rows in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}

    }

}