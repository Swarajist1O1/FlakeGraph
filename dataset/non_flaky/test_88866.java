class DummyClass_88866 {
    @Test
    public void testRemoveNode() {
        UUID nodeId = UUID.randomUUID();

        wrapper.removeNode(nodeId);

        verify(affinityFunction, times(1)).removeNode(eq(nodeId));
    }

}