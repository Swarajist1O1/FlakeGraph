class DummyClass_159584 {
    @Test
    public void zeroColOperations() {
        Table tableZeroCols = new Table();

        // Adds rows.
        try { tableZeroCols.add("val");         fail("No columns in table"); } catch (IndexOutOfBoundsException ignored) {}
        try { tableZeroCols.addEmptyRow();      fail("No columns in table"); } catch (IndexOutOfBoundsException ignored) {}
        try { tableZeroCols.addEmptyRows(10);   fail("No columns in table"); } catch (IndexOutOfBoundsException ignored) {}


        // Col operations
        try { tableZeroCols.removeColumn(0);                fail("No columns in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}
        try { tableZeroCols.renameColumn(0, "newName");     fail("No columns in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}
        try { tableZeroCols.removeColumn(10);               fail("No columns in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}
        try { tableZeroCols.renameColumn(10, "newName");    fail("No columns in table"); } catch (ArrayIndexOutOfBoundsException ignored) {}
    }

}