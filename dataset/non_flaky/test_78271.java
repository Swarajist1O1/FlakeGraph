class DummyClass_78271 {
  @Test
  public void testIntervalWindowPrefixing() {
    StateNamespace window = StateNamespaces.window(intervalCoder, intervalWindow(1000, 87392));
    StateNamespace windowAndTrigger =
        StateNamespaces.windowAndTrigger(intervalCoder, intervalWindow(1000, 87392), 57);
    assertThat(windowAndTrigger.stringKey(), Matchers.startsWith(window.stringKey()));
    assertThat(
        StateNamespaces.global().stringKey(),
        Matchers.not(Matchers.startsWith(window.stringKey())));
  }

}