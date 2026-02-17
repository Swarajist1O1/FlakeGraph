class DummyClass_92592 {
  @Test
  public void testUsesCorrectClassLoaderWhenThreadClassLoaderIsNull() throws ClassNotFoundException {
	Thread.currentThread().setContextClassLoader(null);
	TypeFactory spySut = spy(mapper.getTypeFactory().withModifier(typeModifier).withClassLoader(classLoader));
	Class<?> clazz = spySut.findClass(aClassName);
	verify(spySut).getClassLoader();
	verify(spySut).classForName(any(String.class), any(Boolean.class), eq(classLoader));
	Assert.assertNotNull(clazz);
	Assert.assertEquals(classLoader, spySut.getClassLoader());
	Assert.assertEquals(typeModifier,spySut._modifiers[0]);
	Assert.assertEquals(null, Thread.currentThread().getContextClassLoader());
  }

}