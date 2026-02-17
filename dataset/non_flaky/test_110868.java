class DummyClass_110868 {
    @Test
    public void deleteEmission() {
        final List<User> usersThatShouldBeSaved = TestFactory.newUsers(10);
        final List<User> usersThatShouldBeDeleted = TestFactory.newUsers(10);
        final List<User> allUsers = new ArrayList<User>();

        allUsers.addAll(usersThatShouldBeSaved);
        allUsers.addAll(usersThatShouldBeDeleted);

        putUsersBlocking(allUsers);

        final Queue<List<User>> expectedUsers = new LinkedList<List<User>>();

        expectedUsers.add(allUsers);
        expectedUsers.add(usersThatShouldBeSaved);

        final EmissionChecker emissionChecker = new EmissionChecker(expectedUsers);
        final Subscription subscription = emissionChecker.subscribe();

        // Should receive all users
        emissionChecker.awaitNextExpectedValue();

        deleteUsersBlocking(usersThatShouldBeDeleted);

        // Should receive users that should be saved
        emissionChecker.awaitNextExpectedValue();

        emissionChecker.assertThatNoExpectedValuesLeft();

        subscription.unsubscribe();
    }

}