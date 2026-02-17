class DummyClass_86120 {
    @Test
    public void listExcerpts() {
        final Set<EntityExcerpt> excerpts = facade.listEntityExcerpts();
        final EntityExcerpt excerpt = excerpts.iterator().next();
        assertThat(excerpt.title()).isEqualTo("title");
        assertThat(excerpt.id()).isEqualTo(ModelId.of("5d4032513d2746703d1467f6"));
        assertThat(excerpt.type()).isEqualTo(ModelTypes.EVENT_DEFINITION_V1);
    }

}