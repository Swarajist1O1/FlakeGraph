class DummyClass_77551 {
    @Test
        public void run(Configuration configuration, Environment environment) throws Exception {
            environment.jersey().register(new TestResource());
        }

}