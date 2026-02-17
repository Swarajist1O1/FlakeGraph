class DummyClass_19467 {
	@Test public void testSimple() throws Exception {
			public Injector createInjector() {
				return Guice.createInjector(new org.eclipse.xtext.index.IndexTestLanguageRuntimeModule(){
					@Override
					public java.lang.Class<? extends org.eclipse.xtext.scoping.IScopeProvider> bindIScopeProvider() {
						return OptimizedScopeProvider.class;
					}
				}
				);
			}

}