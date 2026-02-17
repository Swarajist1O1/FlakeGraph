class DummyClass_110166 {
	@Test(expected = IOException.class)
	public void NoCreateFileAtomicInputStreamReadOnly() throws IOException {
		ByteArrayInputStream in = new ByteArrayInputStream(
				"new contents".getBytes(StandardCharsets.UTF_8));
		dm.createFileAtomic("new-test-file.txt", in);
	}

}