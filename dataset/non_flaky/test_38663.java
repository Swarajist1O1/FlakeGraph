class DummyClass_38663 {
    @Test
    public void shouldStartWithEmptyTopicAndStoreDataAndRecoverAllState() throws Exception {
        // Create the empty topic ...
        testHistoryTopicContent(false, true);
    }

}