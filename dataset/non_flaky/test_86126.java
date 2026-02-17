class DummyClass_86126 {
    @Test
    public void testWithMessageContext() {
        final String fieldValueString = "world";
        final String expectedLookupValue = "lookup-world";

        final TestEvent event = new TestEvent();
        final Message message = newMessage(ImmutableMap.of("hello", fieldValueString));
        final EventWithContext eventWithContext = EventWithContext.create(event, message);

        final LookupTableFieldValueProvider.Config config = newConfig("test", "hello");

        setupMocks("test");
        when(lookupTableFunction.lookup("world")).thenReturn(LookupResult.single("lookup-" + message.getField("hello")));

        final FieldValue fieldValue = newProvider(config).doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo(expectedLookupValue);
    }

}