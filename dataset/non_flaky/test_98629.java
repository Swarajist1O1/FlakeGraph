class DummyClass_98629 {
    @Test
    public void testGetLogger() {
        LogAdapter logAdapter = new SystemLogAdapter();
        assertNotNull(logAdapter.getLogger(Log.class.getName()));
    }

}