class DummyClass_98272 {
  @Test
  public void testJsonAlways() throws Exception {
    final HostConfig hostConfig = objectMapper
        .readValue(fixture("fixtures/hostConfig/restartPolicyAlways.json"),
                   HostConfig.class);
    assertThat(hostConfig.restartPolicy(), is(HostConfig.RestartPolicy.always()));
  }

}