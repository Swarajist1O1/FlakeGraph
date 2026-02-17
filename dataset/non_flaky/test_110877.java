class DummyClass_110877 {
    @Test
    public void queryNumberOfResultsAsSingle() {
        final List<User> users = putUsersBlocking(3);

        final Single<Integer> usersSingle = storIOSQLite
                .get()
                .numberOfResults()
                .withQuery(UserTableMeta.QUERY_ALL)
                .prepare()
                .asRxSingle();

        TestSubscriber<Integer> testSubscriber = new TestSubscriber<Integer>();
        usersSingle.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(users.size());
        testSubscriber.assertCompleted();
    }

}