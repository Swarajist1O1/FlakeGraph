class DummyClass_159565 {
    @Test
    public void addRemoveListenerConcurrency() {
        final Realm realm = looperThread.getRealm();
        final AtomicInteger counter1 = new AtomicInteger(0);
        final AtomicInteger counter2 = new AtomicInteger(0);
        final AtomicInteger counter3 = new AtomicInteger(0);

        // At least we need 2 listeners existing in the list to make sure
        // the iterator.next get called.

        // This one will be added when listener2's onChange called.
        final RealmChangeListener<Realm> listener1 = new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm object) {
                // Step 7: Last listener called. Should only be called once.
                counter1.incrementAndGet();

                // after listener2.onChange
                // Since duplicated entries will be ignored, we still have:
                // [listener2, listener1].
                assertEquals(1, counter1.get());
                assertEquals(2, counter2.get());
                assertEquals(1, counter3.get());
                looperThread.testComplete();
            }

}