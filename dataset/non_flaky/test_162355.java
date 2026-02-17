class DummyClass_162355 {
    @Test
    public void testEvents() {
        RecordingAction.reset();
        List<ActionExecution> aes = new ArrayList<>();
        aes.addAll( am.fireEvent("test/foo", createParams("")) );
        aes.addAll( am.fireEvent("miss/foo", createParams("")) );
        aes.addAll( am.fireEvent("test/bar", createParams("")) );
        for (ActionExecution ae : aes) {
            ae.waitForCompletion();
        }
        List<String> firings = RecordingAction.getMessages();
        assertEquals(2, firings.size());
        assertTrue( 
                (firings.get(0).equals("fired - test/foo") && firings.get(1).equals("fired - test/bar")) 
                ||
                (firings.get(0).equals("fired - test/bar") && firings.get(1).equals("fired - test/foo")) 
                );
        RecordingAction.reset();
    }

}