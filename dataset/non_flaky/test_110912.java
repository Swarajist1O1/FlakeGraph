class DummyClass_110912 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTable() {
        putUserBlocking();

        PreparedGetNumberOfResults operation = storIOSQLite
                .get()
                .numberOfResults()
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tableChanges, 1);
    }

}