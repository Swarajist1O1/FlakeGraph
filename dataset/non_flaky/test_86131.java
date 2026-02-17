class DummyClass_86131 {
    @Test
    public void templateWithError() {
        final TestEvent event = new TestEvent();
        final EventWithContext eventWithContext = EventWithContext.create(event, newMessage(ImmutableMap.of("hello", "world")));

        final FieldValue fieldValue = newTemplate("hello: ${source.yolo}", true).doGet("test", eventWithContext);

        assertThat(fieldValue.dataType()).isEqualTo(FieldValueType.ERROR);
    }

}