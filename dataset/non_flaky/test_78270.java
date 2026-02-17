class DummyClass_78270 {
  @Test
  public void testStability() {
    StateNamespace global = StateNamespaces.global();
    StateNamespace intervalWindow =
        StateNamespaces.window(intervalCoder, intervalWindow(1000, 87392));
    StateNamespace intervalWindowAndTrigger =
        StateNamespaces.windowAndTrigger(intervalCoder, intervalWindow(1000, 87392), 57);
    StateNamespace globalWindow =
        StateNamespaces.window(GlobalWindow.Coder.INSTANCE, GlobalWindow.INSTANCE);
    StateNamespace globalWindowAndTrigger =
        StateNamespaces.windowAndTrigger(GlobalWindow.Coder.INSTANCE, GlobalWindow.INSTANCE, 12);

    assertEquals("/", global.stringKey());
    assertEquals("/gAAAAAABVWD4ogU/", intervalWindow.stringKey());
    assertEquals("/gAAAAAABVWD4ogU/1L/", intervalWindowAndTrigger.stringKey());
    assertEquals("//", globalWindow.stringKey());
    assertEquals("//C/", globalWindowAndTrigger.stringKey());
  }

}