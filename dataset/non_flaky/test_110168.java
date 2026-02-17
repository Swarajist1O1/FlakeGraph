class DummyClass_110168 {
	@Test
	public void getCompressionInputStreamGzip() throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStreamWriter ow = new OutputStreamWriter(
				new GzipCompressorOutputStream(out), StandardCharsets.UTF_8);
		ow.write("Test data");
		ow.close();

		ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
		InputStream cin = dm.getCompressorInputStream(in, CompressionType.GZIP);

		assertEquals("Test data",
				new BufferedReader(new InputStreamReader(cin)).readLine());
	}

}