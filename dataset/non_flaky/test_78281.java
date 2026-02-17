class DummyClass_78281 {
  @Test
  public void testCombiningValueEquality() {
    Combine.BinaryCombineIntegerFn maxFn = Max.ofIntegers();
    Coder<Integer> input1 = VarIntCoder.of();
    Coder<Integer> input2 = BigEndianIntegerCoder.of();
    Combine.BinaryCombineIntegerFn minFn = Min.ofIntegers();

    StateTag<?> fooCoder1Max1 = StateTags.combiningValueFromInputInternal("foo", input1, maxFn);
    StateTag<?> fooCoder1Max2 = StateTags.combiningValueFromInputInternal("foo", input1, maxFn);
    StateTag<?> fooCoder1Min = StateTags.combiningValueFromInputInternal("foo", input1, minFn);

    StateTag<?> fooCoder2Max = StateTags.combiningValueFromInputInternal("foo", input2, maxFn);
    StateTag<?> barCoder1Max = StateTags.combiningValueFromInputInternal("bar", input1, maxFn);

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