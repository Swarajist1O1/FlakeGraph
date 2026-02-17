class DummyClass_110838 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTag() {
        User user = putUserBlocking();

        PreparedGetObject<User> operation = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tagChanges, user);
    }

}