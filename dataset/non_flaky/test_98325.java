class DummyClass_98325 {
  @Test
  public void testDefaultCertPath() throws Exception {
    when(systemDelegate.getProperty("user.home")).thenReturn("foobar");
    DockerHost.setSystemDelegate(systemDelegate);

    assertThat(DockerHost.defaultCertPath(), equalTo("foobar/.docker"));
  }

}