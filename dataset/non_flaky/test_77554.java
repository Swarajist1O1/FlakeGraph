class DummyClass_77554 {
    @Test
        public void run(TestConfiguration configuration, Environment environment) throws Exception {
            environment.jersey().register(new TestResource(configuration.getMessage()));
        }

}