class DummyClass_159572 {
    @Test
    public void asyncRealmObjectShouldNotBlockBackgroundCommitNotification() {
        final AtomicInteger numberOfRealmCallbackInvocation = new AtomicInteger(0);
        final CountDownLatch signalClosedRealm = new CountDownLatch(1);
        final Realm realm = looperThread.getRealm();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(final Realm realm) {
                switch (numberOfRealmCallbackInvocation.incrementAndGet()) {
                    case 1: {
                        // First commit.
                        Dog dog = realm.where(Dog.class).findFirstAsync();
                        assertTrue(dog.load());
                        dog.addChangeListener(new RealmChangeListener<Dog>() {
                            @Override
                            public void onChange(Dog dog) {
                            }

}