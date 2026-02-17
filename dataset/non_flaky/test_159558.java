class DummyClass_159558 {
    @Test
    public void removeChangeListener() throws InterruptedException, ExecutionException {
        final AtomicInteger counter = new AtomicInteger(0);
        RealmChangeListener<Realm> listener = new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm object) {
                counter.incrementAndGet();
            }

}