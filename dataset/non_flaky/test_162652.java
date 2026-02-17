class DummyClass_162652 {
  @Test
  public void serverSpan() {
    // SpanKey.SERVER will never be passed to SpanSuppressionStrategy.from(), it cannot be
    // automatically determined by te builder - thus it does not make any sense to test it (for now)
    SpanSuppressionStrategy strategy = SpanSuppressionStrategy.from(emptySet());

    Context context = strategy.storeInContext(Context.root(), SpanKind.SERVER, SPAN);

    assertThat(strategy.shouldSuppress(context, SpanKind.SERVER)).isTrue();
    Stream.of(SpanKind.CLIENT, SpanKind.CONSUMER, SpanKind.PRODUCER)
        .forEach(spanKind -> assertThat(strategy.shouldSuppress(context, spanKind)).isFalse());

    verifySpanKey(SpanKey.SERVER, context);
  }

}