class DummyClass_133907 {
    @Test
    public void testGetSystemInfo() {
        final SystemInfo version = api().systemInfo();
        assertNotNull(version);
        assertTrue(version.jenkinsVersion() != null);
    }

}