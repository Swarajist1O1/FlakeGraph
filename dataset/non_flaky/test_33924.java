class DummyClass_33924 {
    @Test
    public void vaultTrackByWithSortingTest() throws Exception {
        mockResult.expectedMinimumMessageCount(1);
        mockError.expectedMessageCount(0);
        MockEndpoint.assertIsSatisfied(context);
    }

}