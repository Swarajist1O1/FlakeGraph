class DummyClass_110913 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTable() {
        putUserBlocking();

        PreparedGetNumberOfResults operation = storIOSQLite
                .get()
                .numberOfResults()
                .withQuery(rawQuery)
                .prepare();

        verifyChangesReceived(operation, tableChanges, 1);
    }

}