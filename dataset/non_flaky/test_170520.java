class DummyClass_170520 {
    @BeforeEach
    public void setup() throws Exception
    {
        web25 = MavenTestingUtils.getTestResourceFile("web25.xml");
        web31false = MavenTestingUtils.getTestResourceFile("web31false.xml");
        web31true = MavenTestingUtils.getTestResourceFile("web31true.xml");

        // prepare an sci that will be on the webapp's classpath
        jarDir = new File(MavenTestingUtils.getTestResourcesDir().getParentFile(), "jar");
        testSciJar = new File(jarDir, "test-sci.jar");
        assertTrue(testSciJar.exists());

        testContainerSciJar = new File(jarDir, "test-sci-for-container-path.jar");
        testWebInfClassesJar = new File(jarDir, "test-sci-for-webinf.jar");

        // unpack some classes to pretend that are in WEB-INF/classes
        unpacked = new File(MavenTestingUtils.getTargetTestingDir(), "test-sci-for-webinf");
        unpacked.mkdirs();
        FS.cleanDirectory(unpacked);
        JAR.unpack(testWebInfClassesJar, unpacked);
        webInfClasses = Resource.newResource(unpacked);

        containerLoader = new URLClassLoader(new URL[]{
            testContainerSciJar.toURI().toURL()
        }, Thread.currentThread().getContextClassLoader());

        targetClasses = Resource.newResource(MavenTestingUtils.getTargetDir().toURI()).addPath("/test-classes");

        classes = Arrays.asList(new Resource[]{webInfClasses, targetClasses});

        webAppLoader = new URLClassLoader(new URL[]{
            testSciJar.toURI().toURL(), targetClasses.getURI().toURL(), webInfClasses.getURI().toURL()
        },
            containerLoader);
    }

}