class DummyClass_176859 {
  @Test
  public void testHashDouble() {
    for (int i = 0; i < 1000; i++) {
      assertEquals(Double.valueOf(i).hashCode(), LangUtils.hashDouble(i));
    }
  }

}