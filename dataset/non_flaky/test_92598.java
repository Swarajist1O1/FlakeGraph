class DummyClass_92598 {
@Test
public void testSetsTheCorrectClassLoderIfUsingWithClassLoaderFollowedByWithModifier() {
	TypeFactory sut = mapper.getTypeFactory().withClassLoader(classLoader).withModifier(typeModifier);
	Assert.assertNotNull(sut.getClassLoader());
}

}