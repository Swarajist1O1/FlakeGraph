class DummyClass_170458 {
    @Test
    public void testMetaDataCaching()
    {
        Derived derived = new Derived();
        ObjectMBean derivedMBean = (ObjectMBean)container.mbeanFor(derived);
        ObjectMBean derivedMBean2 = (ObjectMBean)container.mbeanFor(derived);
        assertNotSame(derivedMBean, derivedMBean2);
        assertSame(derivedMBean.metaData(), derivedMBean2.metaData());
    }

}