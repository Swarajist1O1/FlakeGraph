class DummyClass_84656 {
    @Test
    public void testNonexistantCommand() throws Exception {
        testInvalidCommand("cret -s /node1", 127);
    }

}