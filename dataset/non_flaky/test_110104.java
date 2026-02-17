class DummyClass_110104 {
	@Test
	public void testCompressionOutputArgumentsShort() {
		String[] args = new String[] { "-a", "json", "-z", "bz2" };
		DumpProcessingOutputAction action = getActionFromArgs(args);

		assertEquals(action.compressionType,
				DumpProcessingOutputAction.COMPRESS_BZ2);
	}

}