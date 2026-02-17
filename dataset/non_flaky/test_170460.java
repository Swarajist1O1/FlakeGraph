class DummyClass_170460 {
    @Test
    public void testDerivedOperations() throws Exception
    {
        Derived derived = new Derived();
        ObjectMBean mbean = (ObjectMBean)container.mbeanFor(derived);

        container.beanAdded(null, derived);

        MBeanInfo info = mbean.getMBeanInfo();
        assertEquals(5, info.getOperations().length, "operation count does not match");

        MBeanOperationInfo[] operationInfos = info.getOperations();
        boolean publish = false;
        boolean doodle = false;
        boolean good = false;
        for (MBeanOperationInfo operationInfo : operationInfos)
        {
            if ("publish".equals(operationInfo.getName()))
            {
                publish = true;
                assertEquals("publish something", operationInfo.getDescription(), "description doesn't match");
            }

            if ("doodle".equals(operationInfo.getName()))
            {
                doodle = true;
                assertEquals("Doodle something", operationInfo.getDescription(), "description doesn't match");
                MBeanParameterInfo[] parameterInfos = operationInfo.getSignature();
                assertEquals("A description of the argument", parameterInfos[0].getDescription(), "parameter description doesn't match");
                assertEquals("doodle", parameterInfos[0].getName(), "parameter name doesn't match");
            }

            // This is a proxied operation on the MBean wrapper.
            if ("good".equals(operationInfo.getName()))
            {
                good = true;
                assertEquals("test of proxy operations", operationInfo.getDescription(), "description does not match");
                assertEquals("not bad", mbean.invoke("good", new Object[]{}, new String[]{}), "execution contexts wrong");
            }
        }

        assertTrue(publish, "publish operation was not not found");
        assertTrue(doodle, "doodle operation was not not found");
        assertTrue(good, "good operation was not not found");
    }

}