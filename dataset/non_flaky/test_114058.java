class DummyClass_114058 {
    @Test
    public void helperCreationMethodsWork() {
        assertThat(EnhancedType.of(String.class).rawClass()).isEqualTo(String.class);

        assertThat(EnhancedType.listOf(String.class)).satisfies(v -> {
            assertThat(v.rawClass()).isEqualTo(List.class);
            assertThat(v.rawClassParameters()).hasSize(1);
            assertThat(v.rawClassParameters().get(0).rawClass()).isEqualTo(String.class);
        });

        assertThat(EnhancedType.mapOf(String.class, Integer.class)).satisfies(v -> {
            assertThat(v.rawClass()).isEqualTo(Map.class);
            assertThat(v.rawClassParameters()).hasSize(2);
            assertThat(v.rawClassParameters().get(0).rawClass()).isEqualTo(String.class);
            assertThat(v.rawClassParameters().get(1).rawClass()).isEqualTo(Integer.class);
        });
    }

}