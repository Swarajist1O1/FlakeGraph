class DummyClass_170468 {
    @BeforeEach
    public void setUp()
    {
        container = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        derivedExtended = new DerivedExtended();
        objectMBean = (ObjectMBean)container.mbeanFor(derivedExtended);
        objectMBeanInfo = objectMBean.getMBeanInfo();
    }

}