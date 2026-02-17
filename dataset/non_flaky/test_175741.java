class DummyClass_175741 {
	@Test
	public void testUnlimitedConnectionsBehaviour() throws CoreException, InterruptedException {
		connector = new SocketListenMultiConnector();
		Map<String, String> arguments = new HashMap<>();
		arguments.put("port", Integer.toString(port));
		arguments.put("connectionLimit", "0");
		connector.connect(arguments, new NullProgressMonitor(), launch);
		Thread.sleep(200);

		for (int i = 0; i < 10; i++) {
			assertTrue("connection " + i + " should succeed", connect());
		}
	}

}