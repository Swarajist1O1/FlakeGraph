class DummyClass_133908 {
    @Test
    public void testQuietDown() {
        RequestStatus success = api().quietDown();
        assertNotNull(success);
        assertTrue(success.value());
    }

}