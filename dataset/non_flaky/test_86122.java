class DummyClass_86122 {
    @Test
    public void resolveNativeEntity() {
        EntityDescriptor eventDescriptor = EntityDescriptor
                .create("5d4032513d2746703d1467f6", ModelTypes.EVENT_DEFINITION_V1);
        EntityDescriptor streamDescriptor = EntityDescriptor
                .create("5cdab2293d27467fbe9e8a72", ModelTypes.STREAM_V1);
        Set<EntityDescriptor> expectedNodes = ImmutableSet.of(eventDescriptor, streamDescriptor);
        Graph<EntityDescriptor> graph = facade.resolveNativeEntity(eventDescriptor);
        assertThat(graph).isNotNull();
        Set<EntityDescriptor> nodes = graph.nodes();
        assertThat(nodes).isEqualTo(expectedNodes);
    }

}