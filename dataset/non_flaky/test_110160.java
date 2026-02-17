class DummyClass_110160 {
	@Test
	public void testSetProxy() {
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
				"test.adress", 8080));
		WebResourceFetcherImpl.setProxy(proxy);
		assertTrue(WebResourceFetcherImpl.hasProxy());
		assertEquals(proxy, WebResourceFetcherImpl.getProxy());
	}

}