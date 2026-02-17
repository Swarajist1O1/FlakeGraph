class DummyClass_170498 {
    @Test
    public void testFindBean()
    {
        managed = getManaged();

        objectName = mbeanContainer.findMBean(managed);
        assertNotNull(objectName);

        assertEquals(managed, mbeanContainer.findBean(objectName), "Bean must be added");
        assertNull(mbeanContainer.findBean(null), "It must return null as there is no bean with the name null");
    }

}