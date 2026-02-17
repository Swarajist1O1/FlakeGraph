class DummyClass_159550 {
    @Test
    public void resortingDates() {
        final int TEST_SIZE = 10;
        final AtomicInteger changeListenerCalled = new AtomicInteger(2);

        final Realm realm = Realm.getInstance(looperThread.createConfiguration());
        realm.setAutoRefresh(true);
        populateDates(realm, TEST_SIZE);

        final Runnable endTest = new Runnable() {
            @Override
            public void run() {
                if (changeListenerCalled.decrementAndGet() == 0) {
                    realm.close();
                    looperThread.testComplete();
                }
            }

}