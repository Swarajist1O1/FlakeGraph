class DummyClass_159560 {
    @Test
    public void notificationsNumber() throws InterruptedException, ExecutionException {
        final CountDownLatch isReady = new CountDownLatch(1);
        final CountDownLatch isRealmOpen = new CountDownLatch(1);
        final AtomicInteger counter = new AtomicInteger(0);
        final Looper[] looper = new Looper[1];
        final RealmChangeListener<Realm> listener = new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm object) {
                counter.incrementAndGet();
            }

}