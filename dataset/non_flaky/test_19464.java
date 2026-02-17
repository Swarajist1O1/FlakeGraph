class DummyClass_19464 {
	@Test
	public void testOneSuitableDelegate_02() {
		final int[] invocationCount = new int[] { 0 };
		AbstractGlobalScopeDelegatingScopeProvider root = new AbstractGlobalScopeDelegatingScopeProvider() {
			
			@Override
			public IScope getScope(EObject context, EReference reference) {
				return IScope.NULLSCOPE;
			}

}