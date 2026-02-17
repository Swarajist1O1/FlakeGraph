class DummyClass_110846 {
    @Test
    public void deleteEmission() {
        final List<User> users = putUsersBlocking(10);

        final Queue<Changes> expectedChanges = new LinkedList<Changes>();
        expectedChanges.add(Changes.newInstance(UserTableMeta.TABLE, UserTableMeta.NOTIFICATION_TAG));

        final EmissionChecker emissionChecker = new EmissionChecker(expectedChanges);
        final Subscription subscription = emissionChecker.subscribe();

        deleteUsersBlocking(users);

        // Should receive changes of Users table
        emissionChecker.awaitNextExpectedValue();

        emissionChecker.assertThatNoExpectedValuesLeft();

        subscription.unsubscribe();
    }

}