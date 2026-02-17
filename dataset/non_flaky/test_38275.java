class DummyClass_38275 {
    @Test
    public void testPrimaryKeyViolation() {
        Cell cell = Cell.create("r1".getBytes(), TransactionConstants.COMMIT_TS_COLUMN);
        keyValueService.putUnlessExists(TransactionConstants.TRANSACTION_TABLE,
            ImmutableMap.of(cell, "v1".getBytes()));
        try {
            keyValueService.putUnlessExists(TransactionConstants.TRANSACTION_TABLE,
                ImmutableMap.of(cell, "v2".getBytes()));
            fail();
        } catch (KeyAlreadyExistsException e) {
            //expected
        }
    }

}