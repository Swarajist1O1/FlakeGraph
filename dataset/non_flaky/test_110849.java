class DummyClass_110849 {
    @Test
    public void queryOrdered() {
        final List<User> users = TestFactory.newUsers(3);

        // Reverse sorting by email before inserting, for the purity of the experiment.
        Collections.reverse(users);

        putUsersBlocking(users);

        final List<User> usersFromQueryOrdered = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .orderBy(UserTableMeta.COLUMN_EMAIL)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQueryOrdered).isNotNull();
        assertThat(usersFromQueryOrdered).hasSize(users.size());

        // Sorting by email for check ordering.
        Collections.sort(users);

        for (int i = 0; i < users.size(); i++) {
            assertThat(usersFromQueryOrdered.get(i)).isEqualTo(users.get(i));
        }
    }

}