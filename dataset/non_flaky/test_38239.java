class DummyClass_38239 {
    @Test
    public void testGetRowsWithSelectedColumns() {
        putTestDataForSingleTimestamp();
        ColumnSelection columns1and2 = ColumnSelection.create(Arrays.asList(column1, column2));
        Map<Cell, Value> values = keyValueService.getRows(TEST_TABLE,
                                                          Arrays.asList(row1, row2),
                                                          columns1and2,
                                                          TEST_TIMESTAMP + 1);
        assertEquals(3, values.size());
        assertEquals(null, values.get(Cell.create(row1, column0)));
        assertArrayEquals(value12, values.get(Cell.create(row1, column2)).getContents());
        assertArrayEquals(value21, values.get(Cell.create(row2, column1)).getContents());
        assertArrayEquals(value22, values.get(Cell.create(row2, column2)).getContents());
    }

}