class DummyClass_112156 {
    @Test
    public void assertGetCurrentMillis() throws Exception {
        assertTrue(timeService.getCurrentMillis() <= System.currentTimeMillis());
    }

}