class DummyClass_110113 {
	@Test
	public void testDefaults() {
		String[] args = new String[] { "-a", "rdf", "--rdftasks", "entities" };
		DumpProcessingOutputAction action = DumpProcessingOutputActionTest
				.getActionFromArgs(args);

		assertTrue(action instanceof RdfSerializationAction);
		assertTrue(action.needsSites());
		assertTrue(action.isReady());
		assertEquals(action.getActionName(), "RdfSerializationAction");
	}

}