class DummyClass_94653 {
  @Test
  public void testBuildsWithJettyBoot() {
    assumeTrue(getPlatform().equals("jdk-with-jetty-boot"));

    assertNotNull(JdkWithJettyBootPlatform.buildIfSupported());
  }

}