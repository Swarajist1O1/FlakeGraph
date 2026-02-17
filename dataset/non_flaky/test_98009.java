class DummyClass_98009 {
    @Test
    public void testTakeUnsubscribesOnFlatMapOfGroupBy() {
        Observable.merge(
                EventStream.getEventStream("HTTP-ClusterA", 50),
                EventStream.getEventStream("HTTP-ClusterB", 20))
                // group by type (2 clusters)
                .groupBy(new Func1<Event, String>() {

                    @Override
                    public String call(Event event) {
                        return event.type;
                    }

}