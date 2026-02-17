class DummyClass_86088 {
    @Test
    public void schedule() {
        assertThat(eventDefinitionService.get("54e3deadbeefdeadbeef0000")).isPresent();
        assertThat(jobDefinitionService.streamAll().count()).isEqualTo(0);
        assertThat(jobTriggerService.all()).isEmpty();

        handler.schedule("54e3deadbeefdeadbeef0000");

        assertThat(eventDefinitionService.get("54e3deadbeefdeadbeef0000")).isPresent();

        assertThat(jobDefinitionService.getByConfigField("event_definition_id", "54e3deadbeefdeadbeef0000"))
                .get()
                .satisfies(definition -> {
                    assertThat(definition.title()).isEqualTo("Test");
                    assertThat(definition.description()).isEqualTo("A test event definition");
                    assertThat(definition.config()).isInstanceOf(EventProcessorExecutionJob.Config.class);

                    final EventProcessorExecutionJob.Config config = (EventProcessorExecutionJob.Config) definition.config();


                    assertThat(config.processingWindowSize()).isEqualTo(300000);
                    assertThat(config.processingHopSize()).isEqualTo(60000);

                    assertThat(jobTriggerService.nextRunnableTrigger()).get().satisfies(trigger -> {
                        assertThat(trigger.jobDefinitionId()).isEqualTo(definition.id());
                        assertThat(trigger.schedule()).isInstanceOf(IntervalJobSchedule.class);

                        final IntervalJobSchedule schedule = (IntervalJobSchedule) trigger.schedule();

                        assertThat(schedule.interval()).isEqualTo(60000);
                        assertThat(schedule.unit()).isEqualTo(TimeUnit.MILLISECONDS);
                    });
                });


        assertThat(jobDefinitionService.get("54e3deadbeefdeadbeef0001")).isNotPresent();
        assertThat(jobTriggerService.get("54e3deadbeefdeadbeef0002")).isNotPresent();
    }

}