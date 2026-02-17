class DummyClass_98282 {
  @Test
  public void serializationRoundTripTest() throws Exception {
    // Test serializing and deserializing the same Event instance works and preserves data
    final Event event = Event.create("create", "foo", "nginx", Event.Type.CONTAINER, "create",
        Event.Actor.create("bar", ImmutableMap.of("image", "nginx", "name", "docker-nginx")),
        new Date(1487356000), 100L);

    final ObjectMapper mapper = ObjectMapperProvider.objectMapper();

    final String json = mapper.writeValueAsString(event);

    final Event event2 = mapper.readValue(json, Event.class);
    assertThat(event, equalTo(event2));
  }

}