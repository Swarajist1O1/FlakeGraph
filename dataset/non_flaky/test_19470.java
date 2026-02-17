class DummyClass_19470 {
	@Test public void testIssuesInOtherResource() throws Exception {
			public Injector createInjector() {
				return Guice.createInjector(new org.eclipse.xtext.XtextRuntimeModule() {
					@Override
					public void configureFileExtensions(Binder binder) {
						binder.bind(String.class).annotatedWith(Names.named(Constants.FILE_EXTENSIONS)).toInstance("xtexterror");
					}

}