class DummyClass_59656 {
	@Test
	public void testCGLIBProxy() {
		TagObj target = new TagObj();
		//ç®æ ç±»è®¾ç½®æ è®°
		target.setTag("tag");

		TagObj proxy = ProxyUtil.proxy(target, TimeIntervalAspect.class);
		//ä»£çç±»è·åæ è®°tag (æ­è¨éè¯¯)
		Assert.assertEquals("tag", proxy.getTag());
	}

}