class DummyClass_42976 {
	@Test
	public void testNotEqualMultipleVerbs() {
		first = new ApiMethodDoc();
		first.setPath(Sets.newHashSet("/first"));
		first.setVerb(Sets.newHashSet(ApiVerb.GET, ApiVerb.POST));
		second = new ApiMethodDoc();
		second.setPath(Sets.newHashSet("/second"));
		second.setVerb(Sets.newHashSet(ApiVerb.GET, ApiVerb.POST));
		Assert.assertNotEquals(0, first.compareTo(second));
		
		second.setPath(Sets.newHashSet("/first"));
		second.setVerb(Sets.newHashSet(ApiVerb.PUT, ApiVerb.POST));
		Assert.assertNotEquals(0, first.compareTo(second));
	}

}