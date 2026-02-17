class DummyClass_19478 {
	@Test public void testParseClassPath() throws Exception {
	public String pathTo(String string) throws Exception {
//		URL resource = getClass().getClassLoader().getResource();
		File base = new File("./src/"+getClass().getName().replace('.', '/') + ".java");
		URI fileURI = URI.createFileURI(base.getAbsolutePath());
//		System.out.println(fileURI);
		// this is a hack used in order to get a file URI for a bundleresource:/ URL
//		File f = (File) get(resource,"handler.bundleEntry.file");
//		if (f!=null)
//			fileURI = URI.createFileURI(f.getAbsolutePath());
		
		URI fileURI2 = URI.createURI(string);
		return fileURI2.resolve(fileURI).toFileString();
	}

}