class DummyClass_110873 {
    @Test
    public void queryOneExistedObjectTableUpdate() {
        User expectedUser = User.newInstance(null, "such@email.com");
        putUsersBlocking(3);

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
                .take(2);

        TestSubscriber<User> testSubscriber = new TestSubscriber<User>();
        userObservable.subscribe(testSubscriber);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(null);

        putUserBlocking(expectedUser);

        testSubscriber.awaitTerminalEvent(5, SECONDS);
        testSubscriber.assertNoErrors();
        testSubscriber.assertValues(null, expectedUser);
    }

}