class DummyClass_110123 {
	@Test
	public void testSitesActionException() throws ParseException, IOException {
		Mockito.doThrow(new IOException()).when(mockDpc).getSitesInformation();

		String[] args = new String[] { "-a", "rdf", "--rdftasks",
				"items,labels" };
		Client client = new Client(mockDpc, args);
		client.performActions(); // print help

		Mockito.verify(mockDpc, Mockito.never()).processDump(
				Mockito.<MwDumpFile> any());
		Mockito.verify(mockDpc).getSitesInformation();
	}

}