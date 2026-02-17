class DummyClass_114054 {
    @Test
    public void anonymousCreationCapturesComplexTypeArguments() {
        EnhancedType<Map<String, List<List<String>>>> enhancedType = new EnhancedType<Map<String, List<List<String>>>>(){};
        assertThat(enhancedType.rawClass()).isEqualTo(Map.class);
        assertThat(enhancedType.rawClassParameters().get(0).rawClass()).isEqualTo(String.class);
        assertThat(enhancedType.rawClassParameters().get(1).rawClass()).isEqualTo(List.class);
        assertThat(enhancedType.rawClassParameters().get(1).rawClassParameters().get(0).rawClass()).isEqualTo(List.class);
        assertThat(enhancedType.rawClassParameters().get(1).rawClassParameters().get(0).rawClassParameters().get(0).rawClass())
            .isEqualTo(String.class);
    }

}