class DummyClass_42977 {
	@Test
	public void testEqualMultipleVerbs() {
		first = new ApiMethodDoc();
		first.setPath(Sets.newHashSet("/test"));
		first.setVerb(Sets.newHashSet(ApiVerb.GET, ApiVerb.POST));
		second = new ApiMethodDoc();
		second.setPath(Sets.newHashSet("/test"));
		second.setVerb(Sets.newHashSet(ApiVerb.GET, ApiVerb.POST));
		Assert.assertEquals(0, first.compareTo(second));
		
		second.setVerb(Sets.newHashSet(ApiVerb.POST, ApiVerb.GET));
		Assert.assertEquals(0, first.compareTo(second));
	}

}