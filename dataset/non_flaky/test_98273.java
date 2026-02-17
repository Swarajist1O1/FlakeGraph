class DummyClass_98273 {
  @Test
  public void testJsonUnlessStopped() throws Exception {
    final HostConfig hostConfig = objectMapper
        .readValue(fixture("fixtures/hostConfig/restartPolicyUnlessStopped.json"),
                   HostConfig.class);
    assertThat(hostConfig.restartPolicy(), is(HostConfig.RestartPolicy.unlessStopped()));
  }

}