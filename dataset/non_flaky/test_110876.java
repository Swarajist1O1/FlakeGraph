class DummyClass_110876 {
    @Test
    public void queryObjectAsSingle() {
        final List<User> users = putUsersBlocking(3);

        final Single<User> usersSingle = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(UserTableMeta.QUERY_ALL)
                .prepare()
                .asRxSingle();

        TestSubscriber<User> testSubscriber = new TestSubscriber<User>();
        usersSingle.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValues(users.get(0));
        testSubscriber.assertCompleted();
    }

}