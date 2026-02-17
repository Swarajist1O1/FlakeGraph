class DummyClass_159562 {
    @Test
    public void globalListener_looperThread_triggeredByLocalCommit() {
        final AtomicInteger success = new AtomicInteger(0);
        Realm realm = looperThread.getRealm();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm object) {
                assertEquals(0, success.getAndIncrement());
                looperThread.testComplete();
            }

}