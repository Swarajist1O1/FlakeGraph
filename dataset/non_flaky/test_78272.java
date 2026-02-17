class DummyClass_78272 {
  @Test
  public void testGlobalWindowPrefixing() {
    StateNamespace window =
        StateNamespaces.window(GlobalWindow.Coder.INSTANCE, GlobalWindow.INSTANCE);
    StateNamespace windowAndTrigger =
        StateNamespaces.windowAndTrigger(GlobalWindow.Coder.INSTANCE, GlobalWindow.INSTANCE, 57);
    assertThat(windowAndTrigger.stringKey(), Matchers.startsWith(window.stringKey()));
    assertThat(
        StateNamespaces.global().stringKey(),
        Matchers.not(Matchers.startsWith(window.stringKey())));
  }

}