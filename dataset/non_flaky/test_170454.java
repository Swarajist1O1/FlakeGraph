class DummyClass_170454 {
    @Test
    public void testMBeanForNull()
    {
        Object mBean = container.mbeanFor(null);
        assertNull(mBean);
    }

}