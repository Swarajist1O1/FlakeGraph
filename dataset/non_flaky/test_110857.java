class DummyClass_110857 {
    @Test
    public void queryDistinct() {
        final List<User> users = new ArrayList<User>();

        for (int i = 0; i < 10; i++) {
            users.add(User.newInstance((long) i, "same@email.com"));
        }

        putUsersBlocking(users);

        final GetResolver<User> customGetResolver = new DefaultGetResolver<User>() {
            @NonNull
            @Override
            public User mapFromCursor(@NonNull StorIOSQLite storIOSQLite, @NonNull Cursor cursor) {
                return User.newInstance(null, cursor.getString(cursor.getColumnIndex(UserTableMeta.COLUMN_EMAIL)));
            }

}