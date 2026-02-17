class DummyClass_84622 {
    @Test
    public void testTooManyArguments() {
        assertThrows(IllegalArgumentException.class, () -> {
            String[] args = {"2181", "/data/dir", "60000", "10000", "9999"};
            serverConfig.parse(args);
        });
    }

}