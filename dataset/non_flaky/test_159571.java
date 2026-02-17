class DummyClass_159571 {
    @Test
    public void asyncRealmResultsShouldNotBlockBackgroundCommitNotification() {
        final Realm realm = looperThread.getRealm();
        final RealmResults<Dog> dogs = realm.where(Dog.class).findAllAsync();
        final AtomicBoolean resultsListenerDone = new AtomicBoolean(false);
        final AtomicBoolean realmListenerDone = new AtomicBoolean(false);

        looperThread.keepStrongReference(dogs);
        assertTrue(dogs.load());
        assertEquals(0, dogs.size());
        dogs.addChangeListener(new RealmChangeListener<RealmResults<Dog>>() {
            @Override
            public void onChange(RealmResults<Dog> results) {
                if (dogs.size() == 2) {
                    // Results has the latest changes.
                    resultsListenerDone.set(true);
                    if (realmListenerDone.get()) {
                        looperThread.testComplete();
                    }
                }
            }

}