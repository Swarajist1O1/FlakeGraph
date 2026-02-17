class DummyClass_110841 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTable() {
        User user = putUserBlocking();

        PreparedGetListOfObjects<User> operation = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(rawQuery)
                .prepare();

        verifyChangesReceived(operation, tableChanges, singletonList(user));
    }

}