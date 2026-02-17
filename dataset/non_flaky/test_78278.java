class DummyClass_78278 {
  @Test
  public void testSetEquality() {
    StateTag<?> fooVarInt1 = StateTags.set("foo", VarIntCoder.of());
    StateTag<?> fooVarInt2 = StateTags.set("foo", VarIntCoder.of());
    StateTag<?> fooBigEndian = StateTags.set("foo", BigEndianIntegerCoder.of());
    StateTag<?> barVarInt = StateTags.set("bar", VarIntCoder.of());

    assertEquals(fooVarInt1, fooVarInt2);
    assertNotEquals(fooVarInt1, fooBigEndian);
    assertNotEquals(fooVarInt1, barVarInt);
  }

}