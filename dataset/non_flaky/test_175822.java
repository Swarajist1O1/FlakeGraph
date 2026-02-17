class DummyClass_175822 {
  @Test
  public void testNegate() {
    assertTrue((Boolean) BooleanConverter.negate().convert(Boolean.FALSE));
    assertFalse((Boolean) BooleanConverter.negate().convert(Boolean.TRUE));
  }

}