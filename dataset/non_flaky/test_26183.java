class DummyClass_26183 {
    @Test
    public void testEmptyQueue()
    {
        assertThat(queue.iterator()).toIterable().isEmpty();
    }

}