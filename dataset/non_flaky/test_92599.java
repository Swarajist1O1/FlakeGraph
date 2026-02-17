class DummyClass_92599 {
@Test
public void testThreadContextClassLoaderIsUsedIfNotUsingWithClassLoader() throws ClassNotFoundException {
	TypeFactory spySut = spy(mapper.getTypeFactory());
	Assert.assertNull(spySut.getClassLoader());
	Class<?> clazz = spySut.findClass(aClassName);
	Assert.assertNotNull(clazz);
	verify(spySut).classForName(any(String.class), any(Boolean.class), eq(threadClassLoader));
}

}