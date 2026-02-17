class DummyClass_133921 {
    @Test
    public void testCancelNonExistentQueueItem() throws InterruptedException {
        RequestStatus success = api().cancel(123456789);
        assertNotNull(success);
        assertTrue(success.value());
        assertTrue(success.errors().isEmpty());
    }

}