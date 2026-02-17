class DummyClass_59602 {
	@Test
	public void cpioTest(){
		final File file = FileUtil.file("d:/test/compress/test.cpio");
		StreamArchiver.create(CharsetUtil.CHARSET_UTF_8, ArchiveStreamFactory.CPIO, file)
				.add(FileUtil.file("d:/Java"), (f)->{
					Console.log("Add: {}", f.getPath());
					return true;
				})
				.finish().close();
	}

}