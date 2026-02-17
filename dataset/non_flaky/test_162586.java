class DummyClass_162586 {
  @Test
    public void execute(JobExecutionContext context) {
      GlobalOpenTelemetry.getTracer("jobtracer").spanBuilder("child").startSpan().end();
    }

}