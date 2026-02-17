class DummyClass_59659 {
	@Test
	public void invokeTest() {
		final Object result = ScriptUtil.invoke(ResourceUtil.readUtf8Str("filter1.js"), "filter1", 2, 1);
		Assert.assertTrue((Boolean) result);
	}

}