class DummyClass_170496 {
    @BeforeEach
    public void setUp()
    {
        mbeanServer = ManagementFactory.getPlatformMBeanServer();
        mbeanContainer = new MBeanContainer(mbeanServer);
    }

}