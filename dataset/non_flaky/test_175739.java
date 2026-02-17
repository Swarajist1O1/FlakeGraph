class DummyClass_175739 {
	@Test
	public void testSingleConnectionBehaviour() throws CoreException, InterruptedException {
		connector = new SocketListenMultiConnector();
		Map<String, String> arguments = new HashMap<>();
		arguments.put("port", Integer.toString(port));
		arguments.put("connectionLimit", "1");
		connector.connect(arguments, new NullProgressMonitor(), launch);
		Thread.sleep(200);

		assertTrue("first connect should succeed", connect());
		assertFalse("second connect should fail", connect());
	}

}