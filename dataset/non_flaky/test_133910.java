class DummyClass_133910 {
    @Test(dependsOnMethods = "testAlreadyQuietDown")
    public void testCancelQuietDown() {
        RequestStatus success = api().cancelQuietDown();
        assertNotNull(success);
        assertTrue(success.value());
    }

}