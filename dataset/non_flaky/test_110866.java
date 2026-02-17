class DummyClass_110866 {
    @Test
    public void insertEmission() {
        final List<User> initialUsers = putUsersBlocking(10);
        final List<User> usersForInsert = TestFactory.newUsers(10);
        final List<User> allUsers = new ArrayList<User>(initialUsers.size() + usersForInsert.size());

        allUsers.addAll(initialUsers);
        allUsers.addAll(usersForInsert);

        final Queue<List<User>> expectedUsers = new LinkedList<List<User>>();
        expectedUsers.add(initialUsers);
        expectedUsers.add(allUsers);

        final EmissionChecker emissionChecker = new EmissionChecker(expectedUsers);
        final Subscription subscription = emissionChecker.subscribe();

        // Should receive initial users
        emissionChecker.awaitNextExpectedValue();

        putUsersBlocking(usersForInsert);

        // Should receive initial users + inserted users
        emissionChecker.awaitNextExpectedValue();

        emissionChecker.assertThatNoExpectedValuesLeft();

        subscription.unsubscribe();
    }

}