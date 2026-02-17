class DummyClass_170479 {
    @Test
    public void testInvokeMBeanException()
    {
        ReflectionException e = assertThrows(ReflectionException.class, () -> objectMBean.invoke("doodle2", new Object[0], new String[0]));

        assertNotNull(e, "An ReflectionException must have occurred by now as doodle2() in Derived bean is private");
    }

}