class DummyClass_159559 {
    @Test
    public void addChangeListener_duplicatedListener() {
        final AtomicInteger counter = new AtomicInteger(0);
        RealmChangeListener<Realm> listener = new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm object) {
                counter.incrementAndGet();
            }

}