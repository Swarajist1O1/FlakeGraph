class DummyClass_110915 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTag() {
        putUserBlocking();

        PreparedGetNumberOfResults operation = storIOSQLite
                .get()
                .numberOfResults()
                .withQuery(rawQuery)
                .prepare();

        verifyChangesReceived(operation, tagChanges, 1);
    }

}