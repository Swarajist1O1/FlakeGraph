class DummyClass_177186 {
    @Test
    public void newScope_appliesWhenNoCurrentRequestContext() {
        try (Scope traceContextScope = currentTraceContext.newScope(traceContext)) {
            assertThat(traceContextScope).hasToString("ThreadLocalScope");
            assertThat(currentTraceContext.get()).isEqualTo(traceContext);
        }
    }

}