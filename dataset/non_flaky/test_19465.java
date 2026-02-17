class DummyClass_19465 {
	@Test
	public void testTwoSuitableDelegates_02() {
		final int[] invocationCount = new int[] { 0 };
		AbstractGlobalScopeDelegatingScopeProvider first = new AbstractGlobalScopeDelegatingScopeProvider() {
			
			@Override
			public IScope getScope(EObject context, EReference reference) {
				return IScope.NULLSCOPE;
			}

}