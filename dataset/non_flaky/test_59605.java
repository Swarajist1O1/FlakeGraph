class DummyClass_59605 {
	@Test
	public void sevenZTest(){
		Extractor extractor = CompressUtil.createExtractor(
				CharsetUtil.defaultCharset(),
				FileUtil.file("d:/test/compress/test.7z"));

		extractor.extract(FileUtil.file("d:/test/compress/test2/"));
	}

}