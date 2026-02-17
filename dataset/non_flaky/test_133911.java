class DummyClass_133911 {
    @Test(dependsOnMethods = "testCancelQuietDown")
    public void testAlreadyCanceledQuietDown() {
        RequestStatus success = api().cancelQuietDown();
        assertNotNull(success);
        assertTrue(success.value());
    }

}