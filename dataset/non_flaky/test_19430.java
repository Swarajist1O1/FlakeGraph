class DummyClass_19430 {
	@Test public void testSerialize_02() throws Exception {
	public void _testSerialize_03() throws Exception {
		model.setGenerated(GeneratedEnum.DIFFERENT_NAME);
		String result = serialize(model);
		assertEquals("generated DifferentLiteral", result);
	}

}