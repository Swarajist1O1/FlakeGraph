class DummyClass_78306 {
  @Test
  public void testValueGrouping() {
    MultimapView<String, String> view =
        InMemoryMultimapSideInputView.fromIterable(
            StringUtf8Coder.of(),
            ImmutableList.of(KV.of("A", "a1"), KV.of("A", "a2"), KV.of("B", "b1")));
    assertEquals(view.get("A"), ImmutableList.of("a1", "a2"));
    assertEquals(view.get("B"), ImmutableList.of("b1"));
    assertEquals(view.get("C"), ImmutableList.of());
  }

}