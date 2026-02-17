class DummyClass_94661 {
  @Test
  public void invokeOptionalWithException() throws Exception {
    SubClass2 subClass2 = new SubClass2();
    try {
      THROWS_EXCEPTION.invokeOptional(subClass2);
    } catch (InvocationTargetException expected) {
      assertTrue(expected.getTargetException() instanceof IOException);
    }

    try {
      THROWS_RUNTIME_EXCEPTION.invokeOptional(subClass2);
    } catch (InvocationTargetException expected) {
      assertTrue(expected.getTargetException() instanceof NumberFormatException);
    }
  }

}