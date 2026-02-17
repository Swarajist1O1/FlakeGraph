class DummyClass_98274 {
  @Test
  public void testJsonOnFailure() throws Exception {
    final HostConfig hostConfig = objectMapper
        .readValue(fixture("fixtures/hostConfig/restartPolicyOnFailure.json"),
                   HostConfig.class);
    assertThat(hostConfig.restartPolicy(), is(HostConfig.RestartPolicy.onFailure(5)));
  }

}