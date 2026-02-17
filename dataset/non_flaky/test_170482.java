class DummyClass_170482 {
    @Test
    public void testInvokeNoSuchMethodException()
    {
        // DerivedMBean contains a managed method with the name good,
        // we must call this method without any arguments.
        ReflectionException e = assertThrows(ReflectionException.class, () ->
            objectMBean.invoke("good", new Object[0], new String[]{
                "int aone"
            }));

        assertNotNull(e, "A ReflectionException must have occurred by now as we cannot call a method with wrong signature");
    }

}