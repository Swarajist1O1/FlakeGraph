class DummyClass_78290 {
  @Test
  public void testIsEmpty() {
    SideInputHandler sideInputHandler =
        new SideInputHandler(ImmutableList.of(view1), InMemoryStateInternals.<Void>forKey(null));

    assertFalse(sideInputHandler.isEmpty());

    // create an empty handler
    SideInputHandler emptySideInputHandler =
        new SideInputHandler(ImmutableList.of(), InMemoryStateInternals.<Void>forKey(null));

    assertTrue(emptySideInputHandler.isEmpty());
  }

}