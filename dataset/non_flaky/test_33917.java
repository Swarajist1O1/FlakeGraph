class DummyClass_33917 {
    @AfterEach
    public void tearDown() throws Exception {
        if (camel != null) {
            camel.stop();
        }
    }

}