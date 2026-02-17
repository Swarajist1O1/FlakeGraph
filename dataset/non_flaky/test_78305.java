class DummyClass_78305 {
  @Test
  public void testStructuralKeyEquality() {
    MultimapView<byte[], Integer> view =
        InMemoryMultimapSideInputView.fromIterable(
            ByteArrayCoder.of(),
            ImmutableList.of(KV.of(new byte[] {0x00}, 0), KV.of(new byte[] {0x01}, 1)));
    assertEquals(view.get(new byte[] {0x00}), ImmutableList.of(0));
    assertEquals(view.get(new byte[] {0x01}), ImmutableList.of(1));
    assertEquals(view.get(new byte[] {0x02}), ImmutableList.of());
  }

}