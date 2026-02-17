class DummyClass_110850 {
    @Test
    public void queryOrderedDesc() {
        final List<User> users = TestFactory.newUsers(3);

        // Sorting by email before inserting, for the purity of the experiment.
        Collections.sort(users);

        putUsersBlocking(users);

        final List<User> usersFromQueryOrdered = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .orderBy(UserTableMeta.COLUMN_EMAIL + " DESC")
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQueryOrdered).isNotNull();
        assertThat(usersFromQueryOrdered).hasSize(users.size());

        // Reverse sorting by email for check ordering.
        Collections.reverse(users);

        for (int i = 0; i < users.size(); i++) {
            assertThat(usersFromQueryOrdered.get(i)).isEqualTo(users.get(i));
        }
    }

}