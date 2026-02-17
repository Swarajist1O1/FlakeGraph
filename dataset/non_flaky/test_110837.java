class DummyClass_110837 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTable() {
        User user = putUserBlocking();

        PreparedGetObject<User> operation = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tableChanges, user);
    }

}