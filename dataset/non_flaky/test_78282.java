class DummyClass_78282 {
  @Test
  public void testCombiningValueWithContextEquality() {
    CoderRegistry registry = CoderRegistry.createDefault();

    Combine.BinaryCombineIntegerFn maxFn = Max.ofIntegers();
    Combine.BinaryCombineIntegerFn minFn = Min.ofIntegers();

    Coder<int[]> accum1 = maxFn.getAccumulatorCoder(registry, VarIntCoder.of());
    Coder<int[]> accum2 = minFn.getAccumulatorCoder(registry, BigEndianIntegerCoder.of());

    StateTag<?> fooCoder1Max1 =
        StateTags.combiningValueWithContext("foo", accum1, CombineFnUtil.toFnWithContext(maxFn));
    StateTag<?> fooCoder1Max2 =
        StateTags.combiningValueWithContext("foo", accum1, CombineFnUtil.toFnWithContext(maxFn));
    StateTag<?> fooCoder1Min =
        StateTags.combiningValueWithContext("foo", accum1, CombineFnUtil.toFnWithContext(minFn));

    StateTag<?> fooCoder2Max =
        StateTags.combiningValueWithContext("foo", accum2, CombineFnUtil.toFnWithContext(maxFn));
    StateTag<?> barCoder1Max =
        StateTags.combiningValueWithContext("bar", accum1, CombineFnUtil.toFnWithContext(maxFn));

    // Same name, coder and combineFn
    assertEquals(fooCoder1Max1, fooCoder1Max2);
    assertEquals(
        StateTags.convertToBagTagInternal((StateTag) fooCoder1Max1),
        StateTags.convertToBagTagInternal((StateTag) fooCoder1Max2));
    // Different combineFn, but we treat them as equal since we only serialize the bits.
    assertEquals(fooCoder1Max1, fooCoder1Min);
    assertEquals(
        StateTags.convertToBagTagInternal((StateTag) fooCoder1Max1),
        StateTags.convertToBagTagInternal((StateTag) fooCoder1Min));

    // Different input coder coder.
    assertNotEquals(fooCoder1Max1, fooCoder2Max);
    assertNotEquals(
        StateTags.convertToBagTagInternal((StateTag) fooCoder1Max1),
        StateTags.convertToBagTagInternal((StateTag) fooCoder2Max));

    // These StateTags have different IDs.
    assertNotEquals(fooCoder1Max1, barCoder1Max);
    assertNotEquals(
        StateTags.convertToBagTagInternal((StateTag) fooCoder1Max1),
        StateTags.convertToBagTagInternal((StateTag) barCoder1Max));
  }

}