class DummyClass_98266 {
  @Test
  public void testLoadFromRandomFixture() throws Exception {
    final ContainerState containerState = objectMapper
        .readValue(fixture("fixtures/container-state-random.json"), ContainerState.class);
    assertThat(containerState.paused(), is(false));
    assertThat(containerState.restarting(), is(false));
    assertThat(containerState.running(), is(true));
    assertThat(containerState.exitCode(), is(0));
    assertThat(containerState.pid(), is(27629));
    assertThat(containerState.startedAt(), is(new Date(1412236798929L)));
    assertThat(containerState.finishedAt(), is(new Date(-62135769600000L)));
    assertThat(containerState.error(), is("this is an error"));
    assertThat(containerState.oomKilled(), is(false));
    assertThat(containerState.status(), is("running"));
    
    ContainerState.Health health = containerState.health();
    assertThat(health.failingStreak(), is(1));
    assertThat(health.status(), is("starting"));
    assertThat(health.log().size(), is(1));
    
    ContainerState.HealthLog log = health.log().get(0);
    assertThat(log.start(), is(new Date(1412236801547L)));
    assertThat(log.end(), is(new Date(1412236802697L)));
    assertThat(log.exitCode(), is(1));
    assertThat(log.output(), is("output"));
  }

}