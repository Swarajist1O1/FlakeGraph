class DummyClass_94651 {
  @Test
  public void buildsWhenJdk9() {
    assumeTrue(getPlatform().equals("jdk9"));

    assertNotNull(Jdk9Platform.buildIfSupported());
  }

}