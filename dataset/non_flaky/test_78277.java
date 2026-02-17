class DummyClass_78277 {
  @Test
  public void testBagEquality() {
    StateTag<?> fooVarInt1 = StateTags.bag("foo", VarIntCoder.of());
    StateTag<?> fooVarInt2 = StateTags.bag("foo", VarIntCoder.of());
    StateTag<?> fooBigEndian = StateTags.bag("foo", BigEndianIntegerCoder.of());
    StateTag<?> barVarInt = StateTags.bag("bar", VarIntCoder.of());

    assertEquals(fooVarInt1, fooVarInt2);
    assertNotEquals(fooVarInt1, fooBigEndian);
    assertNotEquals(fooVarInt1, barVarInt);
  }

}