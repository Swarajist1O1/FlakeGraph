class DummyClass_38263 {
    @Test
    public void testCycleWithReadOnly() {
        // readOnly has a r/w dep on t2 and t2 has a r/w on t1 and t1 has a w/r dep on readOnly
        // This creates a cycle that is valid under SI, but not SSI
        // The main issue is that readOnly reads an invalid state of the world. because it reads the updated value of
        // t1, but the old value of t2.

        String initialValue = "100";
        String newValue = "101";
        Transaction t0 = startTransaction();
        put(t0, "row1", "col1", initialValue);
        put(t0, "row2", "col1", initialValue);
        t0.commit();

        Transaction t1 = startTransaction();
        put(t1, "row1", "col1", newValue);
        Transaction t2 = startTransaction();
        String row1Get = get(t2, "row1", "col1");
        assertEquals(initialValue, row1Get);
        put(t2, "row2", "col1", row1Get);

        t1.commit();
        Transaction readOnly = startTransaction();
        assertEquals(newValue, get(readOnly, "row1", "col1"));
        assertEquals(initialValue, get(readOnly, "row2", "col1"));

        try {
            t2.commit();
            fail();
        } catch (TransactionSerializableConflictException e) {
            // this is expectecd to throw because it is a write skew
        }
    }

}