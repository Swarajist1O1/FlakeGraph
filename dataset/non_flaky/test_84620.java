class DummyClass_84620 {
    @Test
    public void testFewArguments() {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] args = {"2181"};
            serverConfig.parse(args);
        });
    }

}