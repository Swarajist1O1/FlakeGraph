class DummyClass_59604 {
	@Test
	public void zipTest(){
		Extractor extractor = CompressUtil.createExtractor(
				CharsetUtil.defaultCharset(),
				FileUtil.file("d:/test/compress/test.zip"));

		extractor.extract(FileUtil.file("d:/test/compress/test2/"));
	}

}