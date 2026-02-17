class DummyClass_110848 {
    @Test
    public void queryOneByField() {
        final List<User> users = putUsersBlocking(3);

        for (User user : users) {
            final List<User> usersFromQuery = storIOSQLite
                    .get()
                    .listOfObjects(User.class)
                    .withQuery(Query.builder()
                            .table(UserTableMeta.TABLE)
                            .where(UserTableMeta.COLUMN_EMAIL + "=?")
                            .whereArgs(user.email())
                            .build())
                    .prepare()
                    .executeAsBlocking();

            assertThat(usersFromQuery).isNotNull();
            assertThat(usersFromQuery).hasSize(1);
            assertThat(usersFromQuery.get(0)).isEqualTo(user);
        }
    }

}