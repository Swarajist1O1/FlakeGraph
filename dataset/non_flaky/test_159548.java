class DummyClass_159548 {
    @Test
    public void resorting() throws InterruptedException {
        final AtomicInteger changeListenerCalled = new AtomicInteger(4);

        final Realm realm = looperThread.getRealm();
        realm.setAutoRefresh(true);

        final Runnable endTest = new Runnable() {
            @Override
            public void run() {
                if (changeListenerCalled.decrementAndGet() == 0) {
                    realm.close();
                    looperThread.testComplete();
                }
            }

}