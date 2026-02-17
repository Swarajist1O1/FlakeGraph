class DummyClass_76760 {
    @AfterEach
    public void cleanup() {
        if (running != null) {
            running.stop();
        }
    }

}