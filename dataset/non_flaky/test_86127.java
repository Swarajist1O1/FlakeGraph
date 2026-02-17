class DummyClass_86127 {
    @Test
    public void testWithEventContext() {
        final String fieldValueString = "event";
        final String expectedLookupValue = "lookup-event";

        final TestEvent event = new TestEvent();
        final TestEvent eventContext = new TestEvent();

        eventContext.setField("hello", FieldValue.string(fieldValueString));

        final EventWithContext eventWithContext = EventWithContext.create(event, eventContext);

        final LookupTableFieldValueProvider.Config config = newConfig("test", "hello");

        setupMocks("test");
        when(lookupTableFunction.lookup(fieldValueString)).thenReturn(LookupResult.single("lookup-" + eventContext.getField("hello").value()));

        final FieldValue fieldValue = newProvider(config).doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo(expectedLookupValue);
    }

}