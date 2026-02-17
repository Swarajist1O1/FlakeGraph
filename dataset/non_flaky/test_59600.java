class DummyClass_59600 {
	@Test
	public void zipTest(){
		final File file = FileUtil.file("d:/test/compress/test.zip");
		StreamArchiver.create(CharsetUtil.CHARSET_UTF_8, ArchiveStreamFactory.ZIP, file)
				.add(FileUtil.file("d:/Java"), (f)->{
					Console.log("Add: {}", f.getPath());
					return true;
				})
				.finish().close();
	}

}