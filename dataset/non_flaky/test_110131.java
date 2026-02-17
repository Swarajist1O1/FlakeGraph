class DummyClass_110131 {
	@Test
	public void testUnknownAction() {
		String[] args = new String[] { "-a", "notImplemented" };
		ClientConfiguration config = new ClientConfiguration(args);
		assertEquals(0, config.getActions().size());
	}

}