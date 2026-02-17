class DummyClass_110864 {
    @Test
    public void deleteOne() {
        final User user = putUserBlocking();

        final Cursor cursorAfterInsert = db.query(UserTableMeta.TABLE, null, null, null, null, null, null);
        assertThat(cursorAfterInsert.getCount()).isEqualTo(1);
        cursorAfterInsert.close();

        deleteUserBlocking(user);

        final Cursor cursorAfterDelete = db.query(UserTableMeta.TABLE, null, null, null, null, null, null);
        assertThat(cursorAfterDelete.getCount()).isEqualTo(0);
        cursorAfterDelete.close();
    }

}