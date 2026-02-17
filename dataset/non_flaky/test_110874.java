class DummyClass_110874 {
    @Test
    public void queryOneNonexistedObjectTableUpdate() {
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
                .take(2);

        TestSubscriber<User> testSubscriber = new TestSubscriber<User>();
        userObservable.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(null);

        putUserBlocking();

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValues(null, null);
    }

}