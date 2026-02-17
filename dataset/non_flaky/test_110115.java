class DummyClass_110115 {
	@Test
	public void testSerializerSetup() {
		String[] args = new String[] { "-a", "rdf", "--stdout", "--rdftasks",
				"properties,labels" };
		RdfSerializationAction action = (RdfSerializationAction) DumpProcessingOutputActionTest
				.getActionFromArgs(args);
		action.open(); // creates and initializes serializer (prints to stdout)
		action.close(); // just to test that this causes no exceptions

		assertTrue(action.needsSites());
		assertEquals(action.serializer.getTasks(),
				RdfSerializer.TASK_PROPERTIES | RdfSerializer.TASK_LABELS);

	}

}