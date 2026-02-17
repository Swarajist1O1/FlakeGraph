class DummyClass_159575 {
    @Test
    public void accessingSyncRealmResultsInsideAnotherResultListener() {
        final Realm realm = looperThread.getRealm();
        final RealmResults<AllTypes> syncResults1 = realm.where(AllTypes.class).findAll();
        final RealmResults<AllTypes> syncResults2 = realm.where(AllTypes.class).findAll();

        looperThread.keepStrongReference(syncResults1);
        syncResults1.addChangeListener(new RealmChangeListener<RealmResults<AllTypes>>() {
            @Override
            public void onChange(RealmResults<AllTypes> element) {
                assertEquals(1, syncResults1.size());
                assertEquals(1, syncResults2.size()); // If syncResults2 is not in sync yet, this will fail.
                looperThread.testComplete();
            }

}