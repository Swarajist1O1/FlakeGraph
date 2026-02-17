class DummyClass_170462 {
    @BeforeEach
    public void prepare() throws Exception
    {
        container = new ContainerLifeCycle();
        mbeanServer = ManagementFactory.getPlatformMBeanServer();
        MBeanContainer mbeanContainer = new MBeanContainer(mbeanServer);
        container.addBean(mbeanContainer);
        container.start();
    }

}