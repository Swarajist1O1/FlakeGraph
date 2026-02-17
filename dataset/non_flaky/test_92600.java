class DummyClass_92600 {
@Test
public void testUsesFallBackClassLoaderIfNoThreadClassLoaderAndNoWithClassLoader() throws ClassNotFoundException {
	Thread.currentThread().setContextClassLoader(null);
	TypeFactory spySut = spy(mapper.getTypeFactory());
	Assert.assertNull(spySut.getClassLoader());
	Assert.assertArrayEquals(null,spySut._modifiers);
	Class<?> clazz = spySut.findClass(aClassName);
	Assert.assertNotNull(clazz);
	verify(spySut).classForName(any(String.class));
}

}