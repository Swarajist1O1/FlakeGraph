class DummyClass_86134 {
    @Test
    public void templateDateFormatting() {
        final TestEvent event = new TestEvent();
        final EventWithContext eventWithContext = EventWithContext.create(event, newMessage(ImmutableMap.of("timestamp", DateTime.parse("2019-07-02T12:21:00.123Z"))));

        final FieldValue fieldValue = newTemplate("timestamp: ${source.timestamp}").doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo("timestamp: 2019-07-02T12:21:00.123Z");
    }

}