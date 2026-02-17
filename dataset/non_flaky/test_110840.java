class DummyClass_110840 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTable() {
        User user = putUserBlocking();

        PreparedGetListOfObjects<User> operation = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tableChanges, singletonList(user));
    }

}