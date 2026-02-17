class DummyClass_86130 {
    @Test
    public void templateWithEventContext() {
        final TestEvent event = new TestEvent();
        final TestEvent eventContext = new TestEvent();

        eventContext.setField("hello", FieldValue.string("event"));

        final EventWithContext eventWithContext = EventWithContext.create(event, eventContext);

        final FieldValue fieldValue = newTemplate("hello: ${source.hello}").doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo("hello: event");
    }

}