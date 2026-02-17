class DummyClass_177189 {
    @Test
    public void newScope_notOnEventLoop() {
        final TraceContext traceContext2 = TraceContext.newBuilder().traceId(1).spanId(2).build();

        try (SafeCloseable requestContextScope = ctx.push()) {
            try (Scope traceContextScope = currentTraceContext.newScope(traceContext)) {
                assertThat(traceContextScope).hasToString("InitialRequestScope");
                assertThat(currentTraceContext.get()).isEqualTo(traceContext);

                when(eventLoop.inEventLoop()).thenReturn(false);
                try (Scope traceContextScope2 = currentTraceContext.newScope(traceContext2)) {
                    assertThat(traceContextScope2).hasToString("ThreadLocalScope");
                    assertThat(currentTraceContext.get()).isEqualTo(traceContext2);
                }
                when(eventLoop.inEventLoop()).thenReturn(true);
                assertThat(currentTraceContext.get()).isEqualTo(traceContext);
            }
            // the first scope is attached to the request context and cleared when that's destroyed
            assertThat(currentTraceContext.get()).isEqualTo(traceContext);
        }
    }

}