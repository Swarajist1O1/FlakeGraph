class DummyClass_170481 {
    @Test
    public void testInvoke() throws Exception
    {
        String value = (String)objectMBean.invoke("good", new Object[0], new String[0]);

        assertEquals("not bad", value, "Method(good) invocation on objectMBean must return not bad");
    }

}