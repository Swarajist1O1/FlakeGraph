class DummyClass_110844 {
    @Test
    public void insertEmission() {
        final List<User> users = TestFactory.newUsers(10);

        final Queue<Changes> expectedChanges = new LinkedList<Changes>();
        expectedChanges.add(Changes.newInstance(UserTableMeta.TABLE, UserTableMeta.NOTIFICATION_TAG));

        final EmissionChecker emissionChecker = new EmissionChecker(expectedChanges);
        final Subscription subscription = emissionChecker.subscribe();

        putUsersBlocking(users);

        // Should receive changes of Users table
        emissionChecker.awaitNextExpectedValue();

        emissionChecker.assertThatNoExpectedValuesLeft();

        subscription.unsubscribe();
    }

}