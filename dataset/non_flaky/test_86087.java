class DummyClass_86087 {
    @Test
    public void delete() {
        assertThat(eventDefinitionService.get("54e3deadbeefdeadbeef0000")).isPresent();
        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isPresent();
        assertThat(jobTriggerService.get("54e3deadbeefdeadbeef0002")).isPresent();

        assertThat(handler.delete("54e3deadbeefdeadbeef0000")).isTrue();

        assertThat(eventDefinitionService.get("54e3deadbeefdeadbeef0000")).isNotPresent();
        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isNotPresent();
        assertThat(jobTriggerService.get("54e3deadbeefdeadbeef0002")).isNotPresent();
    }

}