class DummyClass_159576 {
    @Test
    public void listenersNotAllowedOnIntentServiceThreads() {
        final Realm realm = looperThread.getRealm();
        realm.beginTransaction();
        AllTypes obj = realm.createObject(AllTypes.class);
        realm.commitTransaction();
        RealmResults<AllTypes> results = realm.where(AllTypes.class).findAll();

        // Global listener
        try {
            realm.addChangeListener(new RealmChangeListener<Realm>() {
                @Override
                public void onChange(Realm element) {

                }

}