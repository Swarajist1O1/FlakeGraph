class DummyClass_110922 {
    @Test
    public void build() {
        class TestItem {

        }

        final PutResolver<TestItem> putResolver = mock(PutResolver.class);
        final GetResolver<TestItem> getResolver = mock(GetResolver.class);
        final DeleteResolver<TestItem> deleteResolver = mock(DeleteResolver.class);

        final SQLiteTypeMapping<TestItem> typeMapping = SQLiteTypeMapping.<TestItem>builder()
                .putResolver(putResolver)
                .getResolver(getResolver)
                .deleteResolver(deleteResolver)
                .build();

        assertThat(typeMapping.putResolver()).isSameAs(putResolver);
        assertThat(typeMapping.getResolver()).isSameAs(getResolver);
        assertThat(typeMapping.deleteResolver()).isSameAs(deleteResolver);
    }

}