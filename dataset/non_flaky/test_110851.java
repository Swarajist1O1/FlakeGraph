class DummyClass_110851 {
    @Test
    public void querySingleLimit() {
        putUsersBlocking(10);

        final int limit = 8;
        final List<User> usersFromQuery = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .limit(String.valueOf(limit))
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQuery).isNotNull();
        assertThat(usersFromQuery).hasSize(limit);
    }

}