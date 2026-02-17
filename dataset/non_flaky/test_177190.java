class DummyClass_177190 {
    @Test
    public void newScope_canClearScope() {
        try (SafeCloseable requestContextScope = ctx.push()) {
            try (Scope traceContextScope = currentTraceContext.newScope(traceContext)) {
                try (Scope traceContextScope2 = currentTraceContext.newScope(null)) {
                    assertThat(currentTraceContext.get()).isNull();
                }
                assertThat(currentTraceContext.get()).isEqualTo(traceContext);
            }
        }
    }

}