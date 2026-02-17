class DummyClass_92596 {
@Test
public void testDefaultTypeFactoryNotAffectedByWithConstructors() {
	TypeFactory sut = mapper.getTypeFactory().withModifier(typeModifier).withClassLoader(classLoader);
	Assert.assertEquals(classLoader, sut.getClassLoader());
	Assert.assertEquals(typeModifier,sut._modifiers[0]);
	Assert.assertNull(mapper.getTypeFactory().getClassLoader());
	Assert.assertArrayEquals(null,mapper.getTypeFactory()._modifiers);
}

}