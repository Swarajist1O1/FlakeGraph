class DummyClass_175737 {
	@Test
	public void testDefaultSettings() throws CoreException {
		connector = new SocketListenMultiConnector();
		Map<String, Connector.Argument> defaults = connector.getDefaultArguments();
		assertTrue(defaults.containsKey("connectionLimit"));
		assertEquals(1, ((Connector.IntegerArgument) defaults.get("connectionLimit")).intValue());
	}

}