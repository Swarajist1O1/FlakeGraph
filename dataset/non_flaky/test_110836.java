class DummyClass_110836 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTable() {
        User user = putUserBlocking();

        PreparedGetObject<User> operation = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tableChanges, user);
    }

}