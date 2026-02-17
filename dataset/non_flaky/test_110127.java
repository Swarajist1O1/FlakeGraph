class DummyClass_110127 {
	@Test
	public void testNonExistingLocalDump() {
		String[] args = { "-f", "./asfjl.json" };
		Client client = new Client(mockDpc, args);
		client.performActions();

		Mockito.verify(mockDpc, Mockito.never()).processDump(
				Mockito.<MwDumpFile> any());
	}

}