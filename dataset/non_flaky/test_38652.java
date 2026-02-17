class DummyClass_38652 {
    @Test
    public void testSinkContext() throws Exception {
        SinkContext sinkContext = mock(SinkContext.class);

        Sink testSink = spy(TestSink.class);
        testSink.open(new HashMap<>(), sinkContext);

        verify(sinkContext, times(1)).recordMetric("foo", 1);
    }

}