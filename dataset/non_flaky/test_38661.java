class DummyClass_38661 {
    @Test
    public void testInvalidConfigWillThrownException() throws Exception {
        KafkaAbstractSink sink = new DummySink();
        Map<String, Object> config = new HashMap<>();
        SinkContext sc = new SinkContext() {
            @Override
            public int getInstanceId() {
                return 0;
            }

}