class DummyClass_96954 {
  @Test
  public void testAccept() {
    AvroSerialization<CharSequence> serialization = new AvroSerialization<>();

    assertTrue(serialization.accept(AvroKey.class));
    assertTrue(serialization.accept(AvroValue.class));
    assertFalse(serialization.accept(AvroWrapper.class));
    assertFalse(serialization.accept(String.class));
  }

}