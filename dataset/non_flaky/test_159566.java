class DummyClass_159566 {
    @Test
    public void realmNotificationOrder() {
        // Tests that global notifications are called in the order they are added
        // Test both ways to check accidental ordering from unordered collections.
        final AtomicInteger listenerACalled = new AtomicInteger(0);
        final AtomicInteger listenerBCalled = new AtomicInteger(0);
        final Realm realm = looperThread.getRealm();

        final RealmChangeListener<Realm> listenerA = new RealmChangeListener<Realm>() {

            @Override
            public void onChange(Realm object) {
                int called = listenerACalled.incrementAndGet();
                if (called == 2) {
                    assertEquals(2, listenerBCalled.get());
                    looperThread.testComplete();
                }
            }

}