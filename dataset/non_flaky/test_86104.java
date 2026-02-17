class DummyClass_86104 {
    @Test
    public void findByEventProcessorId() {
        assertThat(stateService.findByEventDefinitionId("54e3deadbeefdeadbeefaff3")).isPresent();

        assertThat(stateService.findByEventDefinitionId("nope")).isNotPresent();

        assertThatThrownBy(() -> stateService.findByEventDefinitionId(null))
                .hasMessageContaining("eventDefinitionId")
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> stateService.findByEventDefinitionId(""))
                .hasMessageContaining("eventDefinitionId")
                .isInstanceOf(IllegalArgumentException.class);
    }

}