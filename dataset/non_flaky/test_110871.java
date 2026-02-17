class DummyClass_110871 {
    @Test
    public void queryOneExistedObjectObservable() {
        final List<User> users = putUsersBlocking(3);
        final User expectedUser = users.get(0);

        final Observable<User> userObservable = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .where(UserTableMeta.COLUMN_EMAIL + "=?")
                        .whereArgs(expectedUser.email())
                        .build())
                .prepare()
                .asRxObservable()
                .take(1);

        TestSubscriber<User> testSubscriber = new TestSubscriber<User>();
        userObservable.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(expectedUser);
    }

}