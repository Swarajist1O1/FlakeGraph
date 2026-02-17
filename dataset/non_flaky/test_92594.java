class DummyClass_92594 {
@Test
public void testCallingOnlyWithModifierGivesExpectedResults(){
	TypeFactory sut = mapper.getTypeFactory().withModifier(typeModifier);
	Assert.assertNull(sut.getClassLoader());
	Assert.assertEquals(typeModifier,sut._modifiers[0]);
}

}