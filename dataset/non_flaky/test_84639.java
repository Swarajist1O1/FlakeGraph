class DummyClass_84639 {
    @Test
    public void testEnforceAuthenticationOldBehaviour() throws Exception {
        Map<String, String> prop = new HashMap<>();
        startServer(prop);
        testEnforceAuthOldBehaviour(false);
    }

}