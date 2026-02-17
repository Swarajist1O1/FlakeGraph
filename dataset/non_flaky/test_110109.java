class DummyClass_110109 {
	@Test
	public void testDefaults() {
		String[] args = new String[] { "-a", "json" };
		DumpProcessingOutputAction action = DumpProcessingOutputActionTest
				.getActionFromArgs(args);

		assertTrue(action instanceof JsonSerializationAction);
		assertFalse(action.needsSites());
		assertTrue(action.isReady());
		assertEquals(action.getActionName(), "JsonSerializationAction");
	}

}