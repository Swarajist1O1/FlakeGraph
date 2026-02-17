class DummyClass_110135 {
	@Test
	public void testOfflineModeArgumentsShort() {
		String[] args = new String[] { "-n" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertTrue(config.getOfflineMode());
	}

}