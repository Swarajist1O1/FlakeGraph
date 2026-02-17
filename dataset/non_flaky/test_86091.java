class DummyClass_86091 {
    @Test
    public void unscheduleWithMissingEventDefinition() {
        final String id = "54e3deadbeefdeadbeef9999";

        // The event definition should not exist so our test works
        assertThat(eventDefinitionService.get(id)).isNotPresent();

        assertThatThrownBy(() -> handler.unschedule(id))
                .hasMessageContaining("doesn't exist")
                .isInstanceOf(IllegalArgumentException.class);
    }

}