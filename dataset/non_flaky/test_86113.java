class DummyClass_86113 {
    @Test
    public void listExcerpts() {
        final Set<EntityExcerpt> excerpts = facade.listEntityExcerpts();
        final EntityExcerpt excerpt = excerpts.iterator().next();
        assertThat(excerpt.title()).isEqualTo("title");
        assertThat(excerpt.id()).isEqualTo(ModelId.of("5d4d33753d27460ad18e0c4d"));
        assertThat(excerpt.type()).isEqualTo(ModelTypes.NOTIFICATION_V1);
    }

}