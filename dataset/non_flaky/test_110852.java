class DummyClass_110852 {
    @Test
    public void queryLimitOffset() {
        final List<User> users = putUsersBlocking(10);

        final int offset = 5;
        final int limit = 3;
        final List<User> usersFromQuery = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .orderBy(UserTableMeta.COLUMN_EMAIL)
                        .limit(offset + ", " + limit)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQuery).isNotNull();
        assertThat(usersFromQuery).hasSize(Math.min(limit, users.size() - offset));

        Collections.sort(users);

        int position = 0;
        for (int i = offset; i < offset + limit; i++) {
            assertThat(usersFromQuery.get(position++)).isEqualTo(users.get(i));
        }
    }

}