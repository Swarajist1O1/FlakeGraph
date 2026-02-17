class DummyClass_110853 {
    @Test
    public void queryIntegerLimit() {
        putUsersBlocking(10);

        final int limit = 8;
        final List<User> usersFromQuery = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .limit(limit)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQuery).isNotNull();
        assertThat(usersFromQuery).hasSize(limit);
    }

}