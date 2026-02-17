class DummyClass_110867 {
    @Test
    public void updateEmission() {
        final List<User> users = putUsersBlocking(10);

        final Queue<List<User>> expectedUsers = new LinkedList<List<User>>();

        final List<User> updatedList = new ArrayList<User>(users.size());

        int count = 1;
        for (User user : users) {
            updatedList.add(User.newInstance(user.id(), "new_email" + count++));
        }
        expectedUsers.add(users);
        expectedUsers.add(updatedList);
        final EmissionChecker emissionChecker = new EmissionChecker(expectedUsers);
        final Subscription subscription = emissionChecker.subscribe();

        // Should receive all users
        emissionChecker.awaitNextExpectedValue();

        storIOSQLite
                .put()
                .objects(updatedList)
                .prepare()
                .executeAsBlocking();

        // Should receive updated users
        emissionChecker.awaitNextExpectedValue();

        emissionChecker.assertThatNoExpectedValuesLeft();

        subscription.unsubscribe();
    }

}