class DummyClass_110863 {
    @Test
    public void queryOneNonExistedObject() {
        putUsersBlocking(3);

        final User userFromQuery = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .where(UserTableMeta.COLUMN_EMAIL + "=?")
                        .whereArgs("some arg")
                        .build())
                .prepare()
                .executeAsBlocking();

        assertThat(userFromQuery).isNull();
    }

}