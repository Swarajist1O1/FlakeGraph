class DummyClass_110872 {
    @Test
    public void queryOneNonExistedObjectObservable() {
        putUsersBlocking(3);

        final Observable<User> userObservable = storIOSQLite
                .get()
                .object(User.class)
                .withQuery(Query.builder()
                        .table(UserTableMeta.TABLE)
                        .where(UserTableMeta.COLUMN_EMAIL + "=?")
                        .whereArgs("some arg")
                        .build())
                .prepare()
                .asRxObservable()
                .take(1);

        TestSubscriber<User> testSubscriber = new TestSubscriber<User>();
        userObservable.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(null);
    }

}