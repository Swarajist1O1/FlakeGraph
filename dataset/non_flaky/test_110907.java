class DummyClass_110907 {
    @Test
    public void shouldPassArgsAsObjects() {
        final User user = putUserBlocking();

        assertThat(user.id()).isNotNull();
        //noinspection ConstantConditions
        final long uid = user.id();

        final String query = "UPDATE " + UserTableMeta.TABLE
                + " SET " + UserTableMeta.COLUMN_ID + " = MIN(" + UserTableMeta.COLUMN_ID + ", ?)";

        storIOSQLite
                .executeSQL()
                .withQuery(
                        RawQuery.builder()
                                .query(query)
                                .args(uid - 1)  // as integer is less, as string is greater
                                .build())
                .prepare()
                .executeAsBlocking();

        List<User> users = getAllUsersBlocking();

        assertThat(users.size()).isEqualTo(1);

        // Was updated, because (uid - 1) passed as object, not string, and (uid - 1) < uid.
        assertThat(users.get(0).id()).isEqualTo(uid - 1);
    }

}