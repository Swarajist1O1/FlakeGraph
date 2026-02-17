class DummyClass_78275 {
  @Test
  public void testFromStringGlobalWindow() {
    assertStringKeyRoundTrips(GlobalWindow.Coder.INSTANCE, StateNamespaces.global());
    assertStringKeyRoundTrips(
        GlobalWindow.Coder.INSTANCE,
        StateNamespaces.window(GlobalWindow.Coder.INSTANCE, GlobalWindow.INSTANCE));
    assertStringKeyRoundTrips(
        GlobalWindow.Coder.INSTANCE,
        StateNamespaces.windowAndTrigger(GlobalWindow.Coder.INSTANCE, GlobalWindow.INSTANCE, 18));
  }

}