class DummyClass_110202 {
	@Test
	public void testExplicitGetters() throws IOException {
		this.dm.setFileContents(this.dmPath
				.resolve("testdump-20150512.json.gz"), "");
		MwLocalDumpFile df = new MwLocalDumpFile(
				"/testdump-20150512.json.gz",
				DumpContentType.SITES, "20150815",
				"wikidatawiki");

		assertEquals("20150815", df.getDateStamp());
		assertEquals("wikidatawiki", df.getProjectName());
		assertEquals(DumpContentType.SITES, df.getDumpContentType());
		String toString = df.toString();

		assertEquals(this.dmPath.resolve("testdump-20150512.json.gz"),
				df.getPath());

		assertTrue(toString.contains("20150815"));
		assertTrue(toString.contains("wikidatawiki"));
		assertTrue(toString.toLowerCase().contains(
				DumpContentType.SITES.toString().toLowerCase()));
	}

}