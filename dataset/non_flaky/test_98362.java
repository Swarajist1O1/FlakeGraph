class DummyClass_98362 {
    @Override @AfterEach
    public void tearDown() {
        try {
            Files.delete(test);
        } catch (Exception ignored) { }
    }

}