class DummyClass_110843 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTag() {
        User user = putUserBlocking();

        PreparedGetListOfObjects<User> operation = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(rawQuery)
                .prepare();

        verifyChangesReceived(operation, tagChanges, singletonList(user));
    }

}