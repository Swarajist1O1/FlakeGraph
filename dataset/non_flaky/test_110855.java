class DummyClass_110855 {
    @Test
    public void queryGroupBy() {
        final List<User> users = TestFactory.newUsers(10);

        for (int i = 0; i < users.size(); i++) {
            final String commonEmail;
            if (i < 3) {
                commonEmail = "first_group@gmail.com";
            } else {
                commonEmail = "second_group@gmail.com";
            }

            users.set(i, User.newInstance(null, commonEmail));
        }

        putUsersBlocking(users);

        final List<User> groupsOfUsers = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .columns(UserTableMeta.COLUMN_EMAIL)
                        .groupBy(UserTableMeta.COLUMN_EMAIL)
                        .build())
                .withGetResolver(new DefaultGetResolver<User>() {
                    @NonNull
                    @Override
                    public User mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
                        return User.newInstance(null, cursor.getString(cursor.getColumnIndex(UserTableMeta.COLUMN_EMAIL)));
                    }

}