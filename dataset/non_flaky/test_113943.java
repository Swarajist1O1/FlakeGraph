class DummyClass_113943 {
	@BeforeEach
	public void beforeEach() {
		// first call the super method
		super.beforeEach();
		// then do processing for this class
		couchbaseTemplate.removeByQuery(User.class).inCollection(collectionName).all();
		couchbaseTemplate.removeByQuery(UserCol.class).inScope(otherScope).inCollection(otherCollection).all();
		ApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
		// seems that @Autowired is not adequate, so ...
		airportRepository = (AirportRepository) ac.getBean("airportRepository");
		userColRepository = (UserColRepository) ac.getBean("userColRepository");
	}

}