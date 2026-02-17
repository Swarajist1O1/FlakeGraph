class DummyClass_175740 {
	@Test
	public void testTwoConnectionsBehaviour() throws CoreException, InterruptedException {
		connector = new SocketListenMultiConnector();
		Map<String, String> arguments = new HashMap<>();
		arguments.put("port", Integer.toString(port));
		arguments.put("connectionLimit", "2");
		connector.connect(arguments, new NullProgressMonitor(), launch);
		Thread.sleep(200);

		assertTrue("first connect should succeed", connect());
		assertTrue("second connect should succeed", connect());
	}

}