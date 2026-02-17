class DummyClass_110875 {
    @Test
    public void queryListOfObjectsAsSingle() {
        final List<User> users = putUsersBlocking(10);

        final Single<List<User>> usersSingle = storIOSQLite
                .get()
                .listOfObjects(User.class)
                .withQuery(UserTableMeta.QUERY_ALL)
                .prepare()
                .asRxSingle();

        TestSubscriber<List<User>> testSubscriber = new TestSubscriber<List<User>>();
        usersSingle.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(users);
        testSubscriber.assertCompleted();
    }

}