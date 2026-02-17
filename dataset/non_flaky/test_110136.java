class DummyClass_110136 {
	@Test
	public void testOfflineModeArgumentsLong() {
		String[] args = new String[] { "--offline" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertTrue(config.getOfflineMode());
	}

}