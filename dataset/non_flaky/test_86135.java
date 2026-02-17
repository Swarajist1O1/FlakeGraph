class DummyClass_86135 {
    @Test
    public void templateBooleanFormatting() {
        final TestEvent event = new TestEvent();
        final EventWithContext eventWithContext = EventWithContext.create(event, newMessage(ImmutableMap.of("success", true)));

        final FieldValue fieldValue = newTemplate("success: ${source.success}").doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo("success: true");
    }

}