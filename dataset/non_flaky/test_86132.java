class DummyClass_86132 {
    @Test
    @Ignore("template engine doesn't support expressions")
    public void templateCalculation() {
        final TestEvent event = new TestEvent();
        final EventWithContext eventWithContext = EventWithContext.create(event, newMessage(ImmutableMap.of("bytes", 1024)));

        final FieldValue fieldValue = newTemplate("${source.bytes / 1024}").doGet("test", eventWithContext);

        assertThat(fieldValue.value()).isEqualTo("1");
    }

}