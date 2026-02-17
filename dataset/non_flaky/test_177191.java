class DummyClass_177191 {
    @Test
    public void newScope_respondsToPing() {
        final PingPongExtra extra = new PingPongExtra();
        final TraceContext extraContext = TraceContext.newBuilder().traceId(1).spanId(1)
                                                      .addExtra(extra).build();

        try (Scope traceContextScope = currentTraceContext.newScope(extraContext)) {
            assertThat(traceContextScope).hasToString("NoopScope");
            assertThat(extra.isPong()).isTrue();
        }
    }

}