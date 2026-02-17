class DummyClass_78232 {
  @Test
  public void startFinishBundleDelegates() {
    PushbackSideInputDoFnRunner runner = createRunner(ImmutableList.of(singletonView));

    assertThat(underlying.started, is(true));
    assertThat(underlying.finished, is(false));
    runner.finishBundle();
    assertThat(underlying.finished, is(true));
  }

}