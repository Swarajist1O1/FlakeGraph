class DummyClass_110865 {
    @Test
    public void deleteCollection() {
        final List<User> allUsers = putUsersBlocking(10);

        final List<User> usersToDelete = new ArrayList<User>();

        for (int i = 0; i < allUsers.size(); i += 2) {  // Delete every second user
            usersToDelete.add(allUsers.get(i));
        }

        final DeleteResults<User> deleteResults = storIOSQLite
                .delete()
                .objects(usersToDelete)
                .prepare()
                .executeAsBlocking();

        final List<User> usersAfterDelete = getAllUsersBlocking();

        assertThat(usersAfterDelete).hasSize(allUsers.size() / 2);

        for (User user : allUsers) {
            final boolean shouldBeDeleted = usersToDelete.contains(user);

            // Check that we deleted what we going to.
            assertThat(deleteResults.wasDeleted(user)).isEqualTo(shouldBeDeleted);

            // Check that we didn't delete users that we didn't want to
            assertThat(usersAfterDelete.contains(user)).isEqualTo(!shouldBeDeleted);
        }
    }

}