class DummyClass_19496 {
	@Test
	public void testEncoding() throws Exception {
		File file = null;
		FileInputStream fileInputStream = null;
		try {
			JavaIoFileSystemAccess fileSystemAccess = new JavaIoFileSystemAccess(
					IResourceServiceProvider.Registry.INSTANCE, new IEncodingProvider() {
						@Override
						public String getEncoding(URI uri) {
							return "ISO-8859-1";
						}

}