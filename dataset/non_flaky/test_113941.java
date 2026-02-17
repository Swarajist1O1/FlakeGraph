class DummyClass_113941 {
	@BeforeEach
	public void beforeEach() {
		context = new CouchbaseMappingContext();
		converter = new MappingCouchbaseConverter(context);
		ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		couchbaseTemplate = (CouchbaseTemplate) ac.getBean(COUCHBASE_TEMPLATE);
	}

}