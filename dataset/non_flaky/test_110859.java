class DummyClass_110859 {
    @Test
    public void queryWithRawQueryAndArguments() {
        final User testUser = User.newInstance(null, "testUserName");

        final List<User> users = TestFactory.newUsers(10);
        users.add(testUser);
        putUsersBlocking(users);

        final String query = "SELECT * FROM " + UserTableMeta.TABLE
                + " WHERE " + UserTableMeta.COLUMN_EMAIL + " LIKE ?";

        final List<User> usersFromQuery = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(RawQuery.builder()
                        .query(query)
                        .args(testUser.email())
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQuery).isNotNull();
        assertThat(usersFromQuery).hasSize(1);
        assertThat(usersFromQuery.get(0)).isEqualTo(testUser);
    }

}