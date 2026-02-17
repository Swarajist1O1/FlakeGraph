class DummyClass_38235 {
    @Test
    public void testGetRowsAllColumns() {
        putTestDataForSingleTimestamp();
        Map<Cell, Value> values = keyValueService.getRows(TEST_TABLE,
                                                          Arrays.asList(row1, row2),
                                                          ColumnSelection.all(),
                                                          TEST_TIMESTAMP + 1);
        assertEquals(4, values.size());
        assertEquals(null, values.get(Cell.create(row1, column1)));
        assertArrayEquals(value10, values.get(Cell.create(row1, column0)).getContents());
        assertArrayEquals(value12, values.get(Cell.create(row1, column2)).getContents());
        assertArrayEquals(value21, values.get(Cell.create(row2, column1)).getContents());
        assertArrayEquals(value22, values.get(Cell.create(row2, column2)).getContents());
    }

}