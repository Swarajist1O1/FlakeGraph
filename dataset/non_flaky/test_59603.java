class DummyClass_59603 {
	@Test
	public void senvenZTest(){
		final File file = FileUtil.file("d:/test/compress/test.7z");
		CompressUtil.createArchiver(CharsetUtil.CHARSET_UTF_8, ArchiveStreamFactory.SEVEN_Z, file)
				.add(FileUtil.file("d:/Java/apache-maven-3.6.3"), (f)->{
					Console.log("Add: {}", f.getPath());
					return true;
				})
				.finish().close();
	}

}