class DummyClass_110163 {
	@Test(expected = IOException.class)
	public void OutputStreamReadOnly() throws IOException {
		dm.getOutputStreamForFile("file.txt");
	}

}