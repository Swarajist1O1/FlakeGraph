class DummyClass_19494 {
	@Test
	public void testDirsAndFilesAreCreated() throws Exception {
		File dir = null;
		File textFile = null;
		File binFile = null;
		try {
			JavaIoFileSystemAccess fileSystemAccess = new JavaIoFileSystemAccess(
					IResourceServiceProvider.Registry.INSTANCE, new IEncodingProvider.Runtime());

			File tmpDir = configureFileSystemAccess(fileSystemAccess);
			fileSystemAccess.generateFile("tmp/X", "XX");
			fileSystemAccess.generateFile("tmp/Y", new StringInputStream("\1\2\3"));

			dir = new File(tmpDir, "tmp");
			assertTrue(dir.exists());
			assertTrue(dir.isDirectory());

			textFile = new File(dir, "X");
			assertTrue(textFile.exists());
			assertTrue(textFile.isFile());
			assertEquals("XX", fileSystemAccess.readTextFile("tmp/X"));

			binFile = new File(dir, "Y");
			assertTrue(binFile.exists());
			assertFalse(fileSystemAccess.isFile("tmp", IFileSystemAccess.DEFAULT_OUTPUT)); // isFile evaluates to false for directories
			assertTrue(fileSystemAccess.isFile("tmp/Y", IFileSystemAccess.DEFAULT_OUTPUT));
			assertTrue(binFile.isFile());
			InputStream stream = fileSystemAccess.readBinaryFile("tmp/Y");
			try {
				assertEquals("\1\2\3", new String(ByteStreams.toByteArray(stream)));
			} finally {
				stream.close();
			}

		} finally {
			try {
				if (textFile != null)
					textFile.delete();
			} finally {
				try {
					if (binFile != null)
						binFile.delete();
				} finally {
					if (dir != null)
						dir.delete();
				}
			}
		}
	}

}