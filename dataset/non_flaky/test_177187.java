class DummyClass_177187 {
    @Test
    public void newScope_appliesWhenCurrentRequestContext() {
        try (SafeCloseable requestContextScope = ctx.push()) {
            try (Scope traceContextScope = currentTraceContext.newScope(traceContext)) {
                assertThat(traceContextScope).hasToString("InitialRequestScope");
                assertThat(currentTraceContext.get()).isEqualTo(traceContext);
            }
        }
    }

}