class DummyClass_110911 {
    @Test
    public void repeatsOperationWithRawQueryByChangeOfTag() {
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

        storIOSQLite.lowLevel().notifyAboutChanges(tagChanges);

        testSubscriber.assertValueCount(2);
    }

}