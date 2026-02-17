class DummyClass_110114 {
	@Test
	public void testDefaultsNoTasks() {
		String[] args = new String[] { "-a", "rdf", "--stdout" };
		DumpProcessingOutputAction action = DumpProcessingOutputActionTest
				.getActionFromArgs(args);
		action.open();
		action.close();

		assertTrue(action instanceof RdfSerializationAction);
		assertFalse(action.needsSites());
		assertFalse(action.isReady());
	}

}