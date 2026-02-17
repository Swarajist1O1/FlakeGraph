class DummyClass_110159 {
	@Test
	public void testSetUserAgent() {
		WebResourceFetcherImpl.setUserAgent("My user agent");
		assertEquals("My user agent", WebResourceFetcherImpl.getUserAgent());
	}

}