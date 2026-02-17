class DummyClass_38271 {
    @Test
    public void testColumnSelection2() {
        String initialValue = "100";
        Transaction t0 = startTransaction();
        put(t0, "row1", "col1", initialValue);
        put(t0, "row1", "col2", initialValue);
        put(t0, "row2", "col1", initialValue);
        t0.commit();

        Transaction t1 = startTransaction();
        BatchingVisitables.copyToList(t1.getRange(TEST_TABLE, RangeRequest.builder().retainColumns(ImmutableList.of(PtBytes.toBytes("col1"))).build()));
        BatchingVisitables.copyToList(t1.getRange(TEST_TABLE, RangeRequest.builder().retainColumns(ImmutableList.of(PtBytes.toBytes("col2"))).build()));

        // We need to do at least one put so we don't get caught by the read only code path
        put(t1, "row22", "col2", initialValue);

        t1.commit();
    }

}