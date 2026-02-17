class DummyClass_110103 {
	@Test
	public void testDefaults() {
		String[] args = new String[] { "-a", "json" };
		DumpProcessingOutputAction action = getActionFromArgs(args);

		assertEquals(action.compressionType,
				DumpProcessingOutputAction.COMPRESS_NONE);
		assertFalse(action.useStdOut);
	}

}