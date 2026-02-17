class DummyClass_110167 {
	@Test
	public void getCompressionInputStreamNone() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(
				"new contents".getBytes(StandardCharsets.UTF_8));
		assertEquals(in, dm.getCompressorInputStream(in, CompressionType.NONE));
	}

}