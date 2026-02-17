class DummyClass_170480 {
    @Test
    public void testInvokeReflectionException()
    {
        MBeanException e = assertThrows(MBeanException.class, () -> objectMBean.invoke("doodle1", new Object[0], new String[0]));

        assertNotNull(e, "MBeanException is null");
    }

}