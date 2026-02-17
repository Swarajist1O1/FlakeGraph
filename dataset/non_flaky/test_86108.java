class DummyClass_86108 {
    @Test
    public void deleteByEventProcessorId() {
        assertThat(stateService.deleteByEventDefinitionId("54e3deadbeefdeadbeefaff3")).isEqualTo(1);
        assertThat(stateService.deleteByEventDefinitionId("nope")).isEqualTo(0);
    }

}