class DummyClass_19497 {
	@Test
	public void testTraceIsCreated() throws Exception {
		File file = null;
		try {

			JavaIoFileSystemAccess fileSystemAccess = new JavaIoFileSystemAccess(
					IResourceServiceProvider.Registry.INSTANCE, new IEncodingProvider.Runtime(),
					new TraceFileNameProvider(), new TraceRegionSerializer());

			File tmpDir = configureFileSystemAccess(fileSystemAccess);
			SourceRelativeURI uri = new SourceRelativeURI(URI.createURI("foo/bar"));
			CharSequenceTraceWrapper wrapper = new CharSequenceTraceWrapper();
			fileSystemAccess.generateFile("tmp/X", wrapper.wrapWithTraceData("XX", uri, 0, 10, 0, 1));

			file = new File(tmpDir, "tmp/X");
			assertTrue(file.exists());
			assertTrue(file.isFile());
			assertEquals("XX", fileSystemAccess.readTextFile("tmp/X"));

			file = new File(tmpDir, "tmp/.X._trace");
			assertTrue(file.exists());
			assertTrue(file.isFile());

		} finally {
			if (file != null)
				file.delete();
		}
	}

}