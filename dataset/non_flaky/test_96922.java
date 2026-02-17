class DummyClass_96922 {
  @Test
  public void testToString() {
    String datum = "my string";
    AvroWrapper<CharSequence> wrapper = new AvroWrapper<>(datum);
    assertEquals(datum, wrapper.toString());
  }

}