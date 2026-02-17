class DummyClass_110862 {
    @Test
    public void queryOneExistedObject() {
        final List<User> users = putUsersBlocking(3);
        final User user = users.get(0);

        final User userFromQuery = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .where(UserTableMeta.COLUMN_EMAIL + "=?")
                        .whereArgs(user.email())
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(userFromQuery).isNotNull();
        assertThat(userFromQuery).isEqualTo(user);
    }

}