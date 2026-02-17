class DummyClass_170504 {
    @Test
    public void testBeanRemoved()
    {
        setUpBeanRemoved();

        mbeanContainer.beanRemoved(null, managed);

        assertNull(mbeanContainer.findMBean(managed), "Bean shouldn't be registered with container as we removed the bean");
    }

}