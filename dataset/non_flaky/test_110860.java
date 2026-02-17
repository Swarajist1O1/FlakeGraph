class DummyClass_110860 {
    @Test
    public void queryWithRawQuerySqlInjectionFail() {
        final List<User> users = putUsersBlocking(10);

        final String query = "SELECT * FROM " + UserTableMeta.TABLE
                + " WHERE " + UserTableMeta.COLUMN_EMAIL + " LIKE ?";

        final String arg = "(DELETE FROM " + UserTableMeta.TABLE + ")";

        storIOSQLite.get()
                .listOfObjects(User.class)
                .withQuery(RawQuery.builder()
                        .query(query)
                        .args(arg)
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(getAllUsersBlocking()).isEqualTo(users);
    }

}