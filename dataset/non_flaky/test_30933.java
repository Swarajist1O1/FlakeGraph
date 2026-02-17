class DummyClass_30933 {
  @Test
  public void shouldExportJobBatchRecordWithOverlappingCustomHeaders() {
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

    await("index templates need to be created").untilAsserted(this::assertIndexSettings);
    final var jobCreated =
        RecordingExporter.jobRecords(JobIntent.CREATED)
            .withProcessInstanceKey(processInstanceKey)
            .getFirst();

    jobWorker =
        exporterBrokerRule.createJobWorker(
            "test", ((client, job) -> client.newCompleteCommand(job.getKey()).send()));

    // then
    final var jobBatchActivated =
        RecordingExporter.jobBatchRecords(JobBatchIntent.ACTIVATED).withType("test").getFirst();

    assertThat(jobBatchActivated.getValue().getJobKeys()).contains(jobCreated.getKey());
    assertRecordExported(jobBatchActivated);
  }

}