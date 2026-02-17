class DummyClass_110845 {
    @Test
    public void updateEmission() {
        final List<User> users = putUsersBlocking(10);
        final List<User> updated = new ArrayList<User>(users.size());

        for (User user : users) {
            updated.add(User.newInstance(user.id(), user.email()));
        }

        final Queue<Changes> expectedChanges = new LinkedList<Changes>();
        expectedChanges.add(Changes.newInstance(UserTableMeta.TABLE, UserTableMeta.NOTIFICATION_TAG));

        final EmissionChecker emissionChecker = new EmissionChecker(expectedChanges);
        final Subscription subscription = emissionChecker.subscribe();

        storIOSQLite
                .put()
                .objects(updated)
                .prepare()
                .executeAsBlocking();

        // Should receive changes of Users table
        emissionChecker.awaitNextExpectedValue();

        emissionChecker.assertThatNoExpectedValuesLeft();

        subscription.unsubscribe();
    }

}