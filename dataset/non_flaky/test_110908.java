class DummyClass_110908 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTable() {
        putUserBlocking();

        TestSubscriber<Cursor> testSubscriber = new TestSubscriber<Cursor>();
        storIOSQLite
                .get()
                .cursor()
                .withQuery(query)
                .prepare()
                .asRxObservable()
                .subscribe(testSubscriber);

        testSubscriber.assertValueCount(1);

        storIOSQLite.lowLevel().notifyAboutChanges(tableChanges);

        testSubscriber.assertValueCount(2);
    }

}