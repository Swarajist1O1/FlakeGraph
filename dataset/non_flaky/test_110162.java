class DummyClass_110162 {
	@Test(expected = IOException.class)
	public void MissingSubdirectoryReadOnly() throws IOException {
		dm.getSubdirectoryManager("1 2 3 not a subdirectory that exists in the test system, hopefully");
	}

}