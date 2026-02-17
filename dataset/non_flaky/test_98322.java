class DummyClass_98322 {
  @Test
  public void testDefaultAddress() throws Exception {
    assertThat(DockerHost.defaultAddress(), equalTo("localhost"));
  }

}