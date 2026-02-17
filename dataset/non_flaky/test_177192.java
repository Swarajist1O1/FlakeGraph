class DummyClass_177192 {
    @Test
    public void shouldSetPongIfOnlyExtra() {
        final PingPongExtra extra = new PingPongExtra();

        final TraceContext context = TraceContext.newBuilder().traceId(1).spanId(1)
                                                 .addExtra(extra).build();

        TraceContextUtil.PingPongExtra.maybeSetPong(context);

        assertThat(extra.isPong()).isTrue();
    }

}