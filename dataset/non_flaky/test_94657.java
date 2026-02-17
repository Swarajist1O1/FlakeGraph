class DummyClass_94657 {
  @Test
  public void invokeWithException() throws Exception {
    SubClass2 subClass2 = new SubClass2();
    try {
      THROWS_EXCEPTION.invoke(subClass2);
    } catch (InvocationTargetException expected) {
      assertTrue(expected.getTargetException() instanceof IOException);
    }

    try {
      THROWS_RUNTIME_EXCEPTION.invoke(subClass2);
    } catch (InvocationTargetException expected) {
      assertTrue(expected.getTargetException() instanceof NumberFormatException);
    }
  }

}