class DummyClass_170503 {
    @Test
    public void testBeanAddedNullCheck()
    {
        setBeanAdded();
        Integer mbeanCount = mbeanServer.getMBeanCount();

        mbeanContainer.beanAdded(null, null);

        assertEquals(mbeanCount, mbeanServer.getMBeanCount(), "MBean count must not change after beanAdded(null, null) call");
    }

}