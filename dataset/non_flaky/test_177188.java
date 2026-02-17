class DummyClass_177188 {
    @Test
    public void newScope_closeDoesntClearFirstScope() {
        final TraceContext traceContext2 = TraceContext.newBuilder().traceId(1).spanId(2).build();

        try (SafeCloseable requestContextScope = ctx.push()) {
            try (Scope traceContextScope = currentTraceContext.newScope(traceContext)) {
                assertThat(traceContextScope).hasToString("InitialRequestScope");
                assertThat(currentTraceContext.get()).isEqualTo(traceContext);

                try (Scope traceContextScope2 = currentTraceContext.newScope(traceContext2)) {
                    assertThat(traceContextScope2).hasToString("RequestContextTraceContextScope");
                    assertThat(currentTraceContext.get()).isEqualTo(traceContext2);
                }
                assertThat(currentTraceContext.get()).isEqualTo(traceContext);
            }
            // the first scope is attached to the request context and cleared when that's destroyed
            assertThat(currentTraceContext.get()).isEqualTo(traceContext);
        }
    }

}