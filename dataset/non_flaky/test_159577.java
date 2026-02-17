class DummyClass_159577 {
    @Test
    public void listenersNotAllowedOnNonLooperThreads() {
        realm = Realm.getInstance(realmConfig);
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