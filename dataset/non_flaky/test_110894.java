class DummyClass_110894 {
    @Test
    public void insertOne() {
        final User user = putUserBlocking();

        // why we created StorIOSQLite: nobody loves nulls
        final Cursor cursor = db.query(UserTableMeta.TABLE, null, null, null, null, null, null);

        // asserting that values was really inserted to db
        assertThat(cursor.getCount()).isEqualTo(1);
        assertThat(cursor.moveToFirst()).isTrue();

        final User insertedUser = UserTableMeta.GET_RESOLVER.mapFromCursor(storIOSQLite, cursor);

        assertThat(insertedUser.id()).isNotNull();
        assertThat(user.equalsExceptId(insertedUser)).isTrue();

        cursor.close();
    }

}