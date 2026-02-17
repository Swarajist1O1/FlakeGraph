class DummyClass_38654 {
    @Test
    public void testSinkContext() throws Exception {
        SourceContext sourceContext = mock(SourceContext.class);

        Source testSource = spy(TestSource.class);
        testSource.open(new HashMap<>(), sourceContext);

        verify(sourceContext, times(1)).recordMetric("foo", 1);
    }

}