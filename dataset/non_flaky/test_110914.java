class DummyClass_110914 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTag() {
        putUserBlocking();

        PreparedGetNumberOfResults operation = storIOSQLite
                .get()
                .numberOfResults()
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tagChanges, 1);
    }

}