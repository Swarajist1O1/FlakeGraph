class DummyClass_176868 {
  @Test(expected = IllegalStateException.class)
  public void tesInvocationException() {
    ClassUtils.loadInstanceOf(String.class.getName(),
                              String.class,
                              new Class<?>[] { char[].class },
                              new Object[] { null });
  }

}