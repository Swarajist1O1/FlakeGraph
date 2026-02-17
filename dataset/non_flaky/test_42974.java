class DummyClass_42974 {
	@Test
	public void testNotEqual() {
		first = new ApiMethodDoc();
		first.setPath(Sets.newHashSet("/first"));
		first.setVerb(Sets.newHashSet(ApiVerb.GET));
		second = new ApiMethodDoc();
		second.setPath(Sets.newHashSet("/second"));
		second.setVerb(Sets.newHashSet(ApiVerb.GET));
		Assert.assertNotEquals(0, first.compareTo(second));
	}

}