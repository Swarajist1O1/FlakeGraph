class DummyClass_92595 {
@Test
public void testCallingOnlyWithClassLoaderGivesExpectedResults(){
	TypeFactory sut = mapper.getTypeFactory().withClassLoader(classLoader);
	Assert.assertNotNull(sut.getClassLoader());
	Assert.assertArrayEquals(null,sut._modifiers);
}

}