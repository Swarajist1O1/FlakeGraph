class DummyClass_77532 {
    @Test
        public void initialize(Bootstrap<TestConfiguration> bootstrap) {
            bootstrap.setConfigurationFactoryFactory(FailingConfigurationFactory::new);
        }

}