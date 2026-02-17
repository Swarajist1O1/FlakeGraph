class DummyClass_92597 {
@Test
public void testSetsTheCorrectClassLoderIfUsingWithModifierFollowedByWithClassLoader() {
	TypeFactory sut = mapper.getTypeFactory().withModifier(typeModifier).withClassLoader(classLoader);
	Assert.assertNotNull(sut.getClassLoader());
}

}