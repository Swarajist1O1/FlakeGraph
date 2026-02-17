class DummyClass_38278 {
    @Test
    public void testKeyValueEmptyRange() {
        putDirect("row1", "col1", "v1", 0);

        byte[] rowBytes = PtBytes.toBytes("row1");
        ImmutableList<RowResult<Value>> list = ImmutableList.copyOf(keyValueService.getRange(TEST_TABLE, RangeRequest.builder().startRowInclusive(rowBytes).endRowExclusive(rowBytes).build(), 1));
        assertTrue(list.isEmpty());
    }

}