class DummyClass_170506 {
    @Test
    public void testDump()
    {
        assertNotNull(mbeanContainer.dump(), "Dump operation shouldn't return null if operation is success");
    }

}