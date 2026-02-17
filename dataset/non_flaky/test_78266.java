class DummyClass_78266 {
  @Test
  public void testInvokesLifecycleMethods() throws Exception {
    DoFn<Integer, String> fn = new LifecycleVerifyingFn();
    try (ProcessFnTester<Integer, String, SomeRestriction, Void, SomeRestrictionTracker> tester =
        new ProcessFnTester<>(
            Instant.now(),
            fn,
            BigEndianIntegerCoder.of(),
            SerializableCoder.of(SomeRestriction.class),
            MAX_OUTPUTS_PER_BUNDLE,
            MAX_BUNDLE_DURATION)) {
      tester.startElement(42, new SomeRestriction());
    }
  }

}