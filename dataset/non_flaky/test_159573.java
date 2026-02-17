class DummyClass_159573 {
    @Test
    public void realmListener_realmResultShouldBeSynced() {
        final Realm realm = looperThread.getRealm();
        final RealmResults<AllTypes> results = realm.where(AllTypes.class).findAll();
        assertEquals(1, results.size());

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AllTypes allTypes = realm.where(AllTypes.class).findFirst();
                assertNotNull(allTypes);
                allTypes.deleteFromRealm();
                assertEquals(0, realm.where(AllTypes.class).count());
            }

}