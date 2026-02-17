class DummyClass_110839 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTag() {
        User user = putUserBlocking();

        PreparedGetObject<User> operation = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tagChanges, user);
    }

}