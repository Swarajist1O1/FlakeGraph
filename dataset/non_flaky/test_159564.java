class DummyClass_159564 {
    @Test
    public void emptyCommitTriggerChangeListener() {
        final RealmChangeListener<Realm> listener = new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm object) {
                looperThread.testComplete();
            }

}