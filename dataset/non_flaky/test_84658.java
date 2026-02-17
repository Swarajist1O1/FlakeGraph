class DummyClass_84658 {
    @Test
    public void testCreateEphemeralCommandWithoutPath() throws Exception {
        testInvalidCommand("create -e ", 1);
    }

}