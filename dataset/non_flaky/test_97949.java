class DummyClass_97949 {
    @Test
    public void testZipObservableOfObservables() {
        EventStream.getEventStream("HTTP-ClusterB", 20)
                .groupBy(new Func1<Event, String>() {

                    @Override
                    public String call(Event e) {
                        return e.instanceId;
                    }

}