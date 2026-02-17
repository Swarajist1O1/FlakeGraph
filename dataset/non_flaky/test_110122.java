class DummyClass_110122 {
	@Test
	public void testSetDumpsDirectoryException() throws ParseException,
			IOException {
		Mockito.doThrow(new IOException("Mock exception for testing."))
				.when(mockDpc).setDownloadDirectory(Mockito.anyString());

		String[] args = new String[] { "-a", "rdf", "--rdftasks",
				"items,labels", "--dumps", "/tmp/" };
		Client client = new Client(mockDpc, args);
		client.performActions(); // print help

		Mockito.verify(mockDpc, Mockito.never()).processDump(
				Mockito.<MwDumpFile> any());
		Mockito.verify(mockDpc, Mockito.never()).getSitesInformation();
	}

}