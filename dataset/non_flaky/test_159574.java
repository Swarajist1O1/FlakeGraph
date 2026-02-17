class DummyClass_159574 {
    @Test
    public void accessingSyncRealmResultInsideAsyncResultListener() {
        final Realm realm = looperThread.getRealm();
        final AtomicInteger asyncResultCallback = new AtomicInteger(0);

        final RealmResults<AllTypes> syncResults = realm.where(AllTypes.class).findAll();

        RealmResults<AllTypes> results = realm.where(AllTypes.class).findAllAsync();
        looperThread.keepStrongReference(results);
        results.addChangeListener(new RealmChangeListener<RealmResults<AllTypes>>() {
            @Override
            public void onChange(RealmResults<AllTypes> results) {
                switch (asyncResultCallback.incrementAndGet()) {
                    case 1:
                        // Called when first async query completes.
                        assertEquals(0, results.size());
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.createObject(AllTypes.class);
                            }

}