class DummyClass_110909 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTable() {
        putUserBlocking();

        TestSubscriber<Cursor> testSubscriber = new TestSubscriber<Cursor>();
        storIOSQLite
                .get()
                .cursor()
                .withQuery(rawQuery)
                .prepare()
                .asRxObservable()
                .subscribe(testSubscriber);

        testSubscriber.assertValueCount(1);

        storIOSQLite.lowLevel().notifyAboutChanges(tableChanges);

        testSubscriber.assertValueCount(2);
    }

}