class DummyClass_110106 {
	@Test
	public void testStdOutOutputArgumentsShort() {
		String[] args = new String[] { "-a", "json", "-s" };
		DumpProcessingOutputAction action = getActionFromArgs(args);

		assertTrue(action.useStdOut);
	}

}