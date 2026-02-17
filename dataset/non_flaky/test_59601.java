class DummyClass_59601 {
	@Test
	public void tarTest(){
		final File file = FileUtil.file("d:/test/compress/test.tar");
		StreamArchiver.create(CharsetUtil.CHARSET_UTF_8, ArchiveStreamFactory.TAR, file)
				.add(FileUtil.file("d:/Java"), (f)->{
					Console.log("Add: {}", f.getPath());
					return true;
				})
				.finish().close();
	}

}