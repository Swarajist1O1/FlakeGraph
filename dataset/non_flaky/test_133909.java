class DummyClass_133909 {
    @Test(dependsOnMethods = "testQuietDown")
    public void testAlreadyQuietDown() {
        RequestStatus success = api().quietDown();
        assertNotNull(success);
        assertTrue(success.value());
    }

}