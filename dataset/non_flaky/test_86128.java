class DummyClass_86128 {
    @Test
    public void testWithMissingLookupTable() {
        final TestEvent event = new TestEvent();
        final EventWithContext eventWithContext = EventWithContext.create(event, newMessage(ImmutableMap.of("hello", "world")));

        final LookupTableFieldValueProvider.Config config = newConfig("test-doesntexist", "hello");

        setupMocks("test");
        when(lookupTableFunction.lookup("world")).thenReturn(LookupResult.single("lookup-world"));

        assertThatThrownBy(() -> newProvider(config).doGet("test", eventWithContext))
                .hasMessageContaining("test-doesntexist")
                .isInstanceOf(IllegalArgumentException.class);
    }

}