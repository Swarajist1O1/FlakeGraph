class DummyClass_42975 {
	@Test
	public void testEqual() {
		first = new ApiMethodDoc();
		first.setPath(Sets.newHashSet("/test"));
		first.setVerb(Sets.newHashSet(ApiVerb.GET));
		second = new ApiMethodDoc();
		second.setPath(Sets.newHashSet("/test"));
		second.setVerb(Sets.newHashSet(ApiVerb.GET));
		Assert.assertEquals(0, first.compareTo(second));
	}

}