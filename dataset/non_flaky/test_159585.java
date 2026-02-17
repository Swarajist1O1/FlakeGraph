class DummyClass_159585 {
    @Test
    public void findFirstNonExisting() {
        Table t = TestHelper.getTableWithAllColumnTypes();
        t.add(new byte[]{1, 2, 3}, true, new Date(1384423149761L), 4.5d, 5.7f, 100, "string");

        assertEquals(-1, t.findFirstBoolean(1, false));
        // FIXME: reenable when core implements find_first_timestamp(): assertEquals(-1, t.findFirstDate(2, new Date(138442314986l)));
        assertEquals(-1, t.findFirstDouble(3, 1.0d));
        assertEquals(-1, t.findFirstFloat(4, 1.0f));
        assertEquals(-1, t.findFirstLong(5, 50));
    }

}