class DummyClass_110124 {
	@Test
	public void testNonSitesAction() throws ParseException, IOException {
		String[] args = new String[] { "-a", "json", "-q" };
		Client client = new Client(mockDpc, args);
		client.performActions(); // print help

		Mockito.verify(mockDpc).processDump(Mockito.<MwDumpFile> any());
		Mockito.verify(mockDpc, Mockito.never()).getSitesInformation();
	}

}