class DummyClass_110897 {
    @Test
    public void insertOneWithNullField() {
        User user = User.newInstance(null, "user@example.com", null); // phone is null
        putUserBlocking(user);

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