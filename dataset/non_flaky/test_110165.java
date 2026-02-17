class DummyClass_110165 {
	@Test(expected = IOException.class)
	public void NoCreateFileInputStreamReadOnly() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(
				"new contents".getBytes(StandardCharsets.UTF_8));
		dm.createFile("new-test-file.txt", in);
	}

}