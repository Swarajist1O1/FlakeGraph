class DummyClass_170470 {
    @Test
    public void testGetAttributeMBeanException() throws Exception
    {
        Attribute attribute = new Attribute("doodle4", "charu");
        objectMBean.setAttribute(attribute);

        MBeanException e = assertThrows(MBeanException.class, () -> objectMBean.getAttribute("doodle4"));

        assertNotNull(e, "An InvocationTargetException must have occurred by now as doodle4() internally throwing exception");
    }

}