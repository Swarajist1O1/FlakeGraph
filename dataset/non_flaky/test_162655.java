class DummyClass_162655 {
  @Test
  public void producerSpan() {
    SpanSuppressionStrategy strategy = SpanSuppressionStrategy.from(singleton(SpanKey.PRODUCER));

    verifyNoSuppression(strategy, Context.root());

    Context context = strategy.storeInContext(Context.root(), SpanKind.PRODUCER, SPAN);

    assertThat(strategy.shouldSuppress(context, SpanKind.SERVER)).isFalse();
    Stream.of(SpanKind.CLIENT, SpanKind.CONSUMER, SpanKind.PRODUCER)
        .forEach(spanKind -> assertThat(strategy.shouldSuppress(context, spanKind)).isTrue());

    verifySpanKey(SpanKey.PRODUCER, context);
  }

}