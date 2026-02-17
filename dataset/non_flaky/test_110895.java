class DummyClass_110895 {
    @Test
    public void insertCollection() {
        final List<User> users = putUsersBlocking(3);

        // asserting that values was really inserted to db
        final Cursor cursor = db.query(UserTableMeta.TABLE, null, null, null, null, null, null);

        assertThat(cursor.getCount()).isEqualTo(users.size());

        for (int i = 0; i < users.size(); i++) {
            assertThat(cursor.moveToNext()).isTrue();
            assertThat(UserTableMeta.GET_RESOLVER.mapFromCursor(storIOSQLite, cursor)).isEqualTo(users.get(i));
        }

        cursor.close();
    }

}