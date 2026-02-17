class DummyClass_78291 {
  @Test
  public void testContains() {
    SideInputHandler sideInputHandler =
        new SideInputHandler(ImmutableList.of(view1), InMemoryStateInternals.<Void>forKey(null));

    assertTrue(sideInputHandler.contains(view1));
    assertFalse(sideInputHandler.contains(view2));
  }

}