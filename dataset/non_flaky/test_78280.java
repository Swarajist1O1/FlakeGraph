class DummyClass_78280 {
  @Test
  public void testWatermarkBagEquality() {
    StateTag<?> foo1 = StateTags.watermarkStateInternal("foo", TimestampCombiner.EARLIEST);
    StateTag<?> foo2 = StateTags.watermarkStateInternal("foo", TimestampCombiner.EARLIEST);
    StateTag<?> bar = StateTags.watermarkStateInternal("bar", TimestampCombiner.EARLIEST);

    StateTag<?> bar2 = StateTags.watermarkStateInternal("bar", TimestampCombiner.LATEST);

    // Same id, same fn.
    assertEquals(foo1, foo2);
    // Different id, same fn.
    assertNotEquals(foo1, bar);
    // Same id, different fn.
    assertEquals(bar, bar2);
  }

}