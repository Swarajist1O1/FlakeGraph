class DummyClass_110121 {
	@Test
	public void testSitesAction() throws ParseException, IOException {
		String[] args = new String[] { "-a", "rdf", "--rdftasks",
				"items,labels" };
		Client client = new Client(mockDpc, args);
		client.performActions();

		Mockito.verify(mockDpc).processDump(Mockito.<MwDumpFile> any());
		Mockito.verify(mockDpc).getSitesInformation();
	}

}