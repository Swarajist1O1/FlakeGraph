class DummyClass_19463 {
	@Test
	public void testOneSuitableDelegate_01() {
		TestableDelegatingScopeProvider root = new TestableDelegatingScopeProvider();
		TestableDelegatingScopeProvider delegating = new TestableDelegatingScopeProvider(root);
		
		delegating.setWrapper(this);
		Assert.assertEquals(1, delegating.invocationCount);
		Assert.assertEquals(1, root.invocationCount);
		
		IDelegatingScopeProvider.setWrapper(delegating, null);
		Assert.assertEquals(2, delegating.invocationCount);
		Assert.assertEquals(2, root.invocationCount);
	}

}