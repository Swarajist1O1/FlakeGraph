class DummyClass_38265 {
    @Test
    public void testNonPhantomRead() {
        String initialValue = "100";
        Transaction t0 = startTransaction();
        put(t0, "row1", "col1", initialValue);
        put(t0, "row2", "col1", initialValue);
        t0.commit();

        Transaction t1 = startTransaction();
        RowResult<byte[]> first = BatchingVisitables.getFirst(t1.getRange(TEST_TABLE, RangeRequest.builder().build()));
        put(t1, "row22", "col1", initialValue);

        Transaction t2 = startTransaction();
        put(t2, "row11", "col1", initialValue);
        t2.commit();

        t1.commit();
    }

}