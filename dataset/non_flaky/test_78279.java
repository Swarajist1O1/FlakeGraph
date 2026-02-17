class DummyClass_78279 {
  @Test
  public void testMapEquality() {
    StateTag<?> fooStringVarInt1 = StateTags.map("foo", StringUtf8Coder.of(), VarIntCoder.of());
    StateTag<?> fooStringVarInt2 = StateTags.map("foo", StringUtf8Coder.of(), VarIntCoder.of());
    StateTag<?> fooStringBigEndian =
        StateTags.map("foo", StringUtf8Coder.of(), BigEndianIntegerCoder.of());
    StateTag<?> fooVarIntBigEndian =
        StateTags.map("foo", VarIntCoder.of(), BigEndianIntegerCoder.of());
    StateTag<?> barStringVarInt = StateTags.map("bar", StringUtf8Coder.of(), VarIntCoder.of());

    assertEquals(fooStringVarInt1, fooStringVarInt2);
    assertNotEquals(fooStringVarInt1, fooStringBigEndian);
    assertNotEquals(fooStringBigEndian, fooVarIntBigEndian);
    assertNotEquals(fooStringVarInt1, fooVarIntBigEndian);
    assertNotEquals(fooStringVarInt1, barStringVarInt);
  }

}