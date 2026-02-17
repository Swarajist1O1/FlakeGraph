class DummyClass_175738 {
	@Test
	public void testDefaultBehaviour() throws CoreException, InterruptedException {
		connector = new SocketListenMultiConnector();
		Map<String, String> arguments = new HashMap<>();
		arguments.put("port", Integer.toString(port));
		connector.connect(arguments, new NullProgressMonitor(), launch);
		Thread.sleep(200);

		assertTrue("first connect should succeed", connect());
		assertFalse("second connect should fail", connect());
	}

}