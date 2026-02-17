class DummyClass_176854 {
  @Test
  public void testUsedMemory() {
    // Reasonable guess
    assertTrue(JVMUtils.getUsedMemory() >= 1L << 20);
  }

}