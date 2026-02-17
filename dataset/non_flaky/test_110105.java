class DummyClass_110105 {
	@Test
	public void testCompressionOutputArgumentsLong() {
		String[] args = new String[] { "-a", "json", "--compression", "GZ" };
		DumpProcessingOutputAction action = getActionFromArgs(args);

		assertEquals(action.compressionType,
				DumpProcessingOutputAction.COMPRESS_GZIP);
	}

}