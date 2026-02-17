class DummyClass_110169 {
	@Test
	public void getCompressionInputStreamBz2() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStreamWriter ow = new OutputStreamWriter(
				new BZip2CompressorOutputStream(out), StandardCharsets.UTF_8);
		ow.write("Test data");
		ow.close();

		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		InputStream cin = dm.getCompressorInputStream(in, CompressionType.BZ2);

		assertEquals("Test data",
				new BufferedReader(new InputStreamReader(cin)).readLine());
	}

}