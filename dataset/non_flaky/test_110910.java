class DummyClass_110910 {
    @Test
    public void repeatsOperationWithQueryByChangeOfTag() {
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

        storIOSQLite.lowLevel().notifyAboutChanges(tagChanges);

        testSubscriber.assertValueCount(2);
    }

}