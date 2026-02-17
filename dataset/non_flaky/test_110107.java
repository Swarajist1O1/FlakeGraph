class DummyClass_110107 {
	@Test
	public void testStdOutOutputArgumentsLong() {
		String[] args = new String[] { "-a", "json", "--stdout" };
		DumpProcessingOutputAction action = getActionFromArgs(args);

		assertTrue(action.useStdOut);
	}

}