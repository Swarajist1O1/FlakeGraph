class DummyClass_78276 {
  @Test
  public void testValueEquality() {
    StateTag<?> fooVarInt1 = StateTags.value("foo", VarIntCoder.of());
    StateTag<?> fooVarInt2 = StateTags.value("foo", VarIntCoder.of());
    StateTag<?> fooBigEndian = StateTags.value("foo", BigEndianIntegerCoder.of());
    StateTag<?> barVarInt = StateTags.value("bar", VarIntCoder.of());

    assertEquals(fooVarInt1, fooVarInt2);
    assertNotEquals(fooVarInt1, fooBigEndian);
    assertNotEquals(fooVarInt1, barVarInt);
  }

}