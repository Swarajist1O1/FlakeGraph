class DummyClass_86129 {
    @Test
    public void templateWithMessageContext() {
        final TestEvent event = new TestEvent();
        final EventWithContext eventWithContext = EventWithContext.create(event, newMessage(ImmutableMap.of("hello", "world")));

        final FieldValue fieldValue = newTemplate("hello: ${source.hello}").doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo("hello: world");
    }

}