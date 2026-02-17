class DummyClass_110901 {
    @Test
    public void updateCollection() {
        final List<User> usersForInsert = TestFactory.newUsers(3);

        final PutResults<User> insertResults = storIOSQLite
                .put()
                .objects(usersForInsert)
                .prepare()
                .executeAsBlocking();

        assertThat(insertResults.numberOfInserts()).isEqualTo(usersForInsert.size());

        final List<User> usersForUpdate = new ArrayList<User>(usersForInsert.size());

        for (int i = 0; i < usersForInsert.size(); i++) {
            usersForUpdate.add(User.newInstance(usersForInsert.get(i).id(), "new" + i + "@email.com" + i));
        }

        final PutResults<User> updateResults = storIOSQLite
                .put()
                .objects(usersForUpdate)
                .prepare()
                .executeAsBlocking();

        assertThat(updateResults.numberOfUpdates()).isEqualTo(usersForUpdate.size());

        final Cursor cursor = db.query(UserTableMeta.TABLE, null, null, null, null, null, null);

        assertThat(cursor.getCount()).isEqualTo(usersForUpdate.size()); // update should not add new rows!

        for (int i = 0; i < usersForUpdate.size(); i++) {
            assertThat(cursor.moveToNext()).isTrue();
            assertThat(UserTableMeta.GET_RESOLVER.mapFromCursor(storIOSQLite, cursor)).isEqualTo(usersForUpdate.get(i));
        }

        cursor.close();
    }

}