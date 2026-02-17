class DummyClass_86089 {
    @Test
    public void scheduleWithMissingEventDefinition() {
        final String id = "54e3deadbeefdeadbeef9999";

        // The event definition should not exist so our test works
        assertThat(eventDefinitionService.get(id)).isNotPresent();

        assertThatThrownBy(() -> handler.schedule(id))
                .hasMessageContaining("doesn't exist")
                .isInstanceOf(IllegalArgumentException.class);
    }

}