class DummyClass_110896 {
    @Test
    public void insertAndDeleteTwice() {
        final User user = TestFactory.newUser();

        for (int i = 0; i < 2; i++) {
            putUserBlocking(user);

            final List<User> existUsers = getAllUsersBlocking();

            assertThat(existUsers).isNotNull();
            assertThat(existUsers).hasSize(1);

            final Cursor cursorAfterPut = db.query(UserTableMeta.TABLE, null, null, null, null, null, null);
            assertThat(cursorAfterPut.getCount()).isEqualTo(1);
            cursorAfterPut.close();

            deleteUserBlocking(user);

            final Cursor cursorAfterDelete = db.query(UserTableMeta.TABLE, null, null, null, null, null, null);
            assertThat(cursorAfterDelete.getCount()).isEqualTo(0);
            cursorAfterDelete.close();
        }
    }

}