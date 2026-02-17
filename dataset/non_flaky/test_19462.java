class DummyClass_19462 {
	@Test
	public void testNoSuitableDelegate() {
		TestableDelegatingScopeProvider testMe = new TestableDelegatingScopeProvider();
		testMe.setWrapper(this);
		Assert.assertEquals(1, testMe.invocationCount);
		
		IDelegatingScopeProvider.setWrapper(testMe, null);
		Assert.assertEquals(2, testMe.invocationCount);
	}

}