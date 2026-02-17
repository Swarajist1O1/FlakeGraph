class DummyClass_30932 {
  @Test
  public void shouldExportJobRecordWithOverlappingCustomHeaders() {
    // when
    exporterBrokerRule.deployProcess(
        Bpmn.createExecutableProcess("process")
            .startEvent()
            .serviceTask(
                "task",
                t -> t.zeebeJobType("test").zeebeTaskHeader("x", "1").zeebeTaskHeader("x.y", "2"))
            .endEvent()
            .done(),
        "process.bpmn");

    final var processInstanceKey = exporterBrokerRule.createProcessInstance("process", Map.of());

    // then
    await("index templates need to be created").untilAsserted(this::assertIndexSettings);
    final var jobCreated =
        RecordingExporter.jobRecords(JobIntent.CREATED)
            .withProcessInstanceKey(processInstanceKey)
            .getFirst();

    assertRecordExported(jobCreated);
  }

}