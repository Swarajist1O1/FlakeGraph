class DummyClass_110842 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTag() {
        User user = putUserBlocking();

        PreparedGetListOfObjects<User> operation = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(query)
                .prepare();

        verifyChangesReceived(operation, tagChanges, singletonList(user));
    }

}