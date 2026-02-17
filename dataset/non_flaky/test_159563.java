class DummyClass_159563 {
    @Test
    public void globalListener_looperThread_triggeredByRemoteCommit() {
        final AtomicInteger success = new AtomicInteger(0);
        Realm realm = looperThread.getRealm();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm object) {
                assertEquals(1, success.get());
                looperThread.testComplete();
            }

}