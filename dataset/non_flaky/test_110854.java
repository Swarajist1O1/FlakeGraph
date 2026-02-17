class DummyClass_110854 {
    @Test
    public void queryLimitOffsetQuantity() {
        final List<User> users = putUsersBlocking(10);

        final int offset = 5;
        final int quantity = 3;
        final List<User> usersFromQuery = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .orderBy(UserTableMeta.COLUMN_EMAIL)
                        .limit(offset, quantity)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(usersFromQuery).isNotNull();
        assertThat(usersFromQuery).hasSize(Math.min(quantity, users.size() - offset));

        Collections.sort(users);

        int position = 0;
        for (int i = offset; i < offset + quantity; i++) {
            assertThat(usersFromQuery.get(position++)).isEqualTo(users.get(i));
        }
    }

}