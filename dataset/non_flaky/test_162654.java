class DummyClass_162654 {
  @ParameterizedTest
  public void clientSpan(SpanKey spanKey) {
    SpanSuppressionStrategy strategy = SpanSuppressionStrategy.from(singleton(spanKey));

    verifyNoSuppression(strategy, Context.root());

    Context context = strategy.storeInContext(Context.root(), SpanKind.CLIENT, SPAN);

    assertThat(strategy.shouldSuppress(context, SpanKind.SERVER)).isFalse();
    Stream.of(SpanKind.CLIENT, SpanKind.CONSUMER, SpanKind.PRODUCER)
        .forEach(spanKind -> assertThat(strategy.shouldSuppress(context, spanKind)).isTrue());

    verifySpanKey(spanKey, context);
  }

}