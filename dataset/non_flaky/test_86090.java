class DummyClass_86090 {
    @Test
    public void unschedule() {
        assertThat(eventDefinitionService.get("54e3deadbeefdeadbeef0000")).isPresent();
        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isPresent();
        assertThat(jobTriggerService.get("54e3deadbeefdeadbeef0002")).isPresent();

        handler.unschedule("54e3deadbeefdeadbeef0000");

        // Unschedule should NOT delete the event definition!
        assertThat(eventDefinitionService.get("54e3deadbeefdeadbeef0000")).isPresent();

        // Only the job definition and the trigger
        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isNotPresent();
        assertThat(jobTriggerService.get("54e3deadbeefdeadbeef0002")).isNotPresent();
    }

}