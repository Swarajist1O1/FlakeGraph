class DummyClass_113938 {
	@BeforeEach
	public void beforeEach() {
		context = new CouchbaseMappingContext();
		converter = new MappingCouchbaseConverter(context);
		bucketName = "sample-bucket";
	}

}