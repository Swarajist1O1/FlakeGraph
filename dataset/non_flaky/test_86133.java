class DummyClass_86133 {
    @Test
    public void templateNumberFormatting() {
        final TestEvent event = new TestEvent();
        final EventWithContext eventWithContext = EventWithContext.create(event, newMessage(ImmutableMap.of("count", 10241234, "avg", 1024.42)));

        final FieldValue fieldValue = newTemplate("count: ${source.count} avg: ${source.avg}").doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo("count: 10241234 avg: 1024.42");
    }

}