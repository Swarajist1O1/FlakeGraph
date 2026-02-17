class DummyClass_95696 {
    @Test
    public void buildWillReturnFeatureGroupWhenFeatureGroupAnnotationIsPresentForClassLevelGroup() throws Exception {
        FeatureGroup result = AnnotationFeatureGroup.build(ClassLevelGroup.class);

        assertThat(result, notNullValue());
        assertThat(result.getLabel(), is(CLASS_LEVEL_GROUP_LABEL));
        assertThat(result.contains(TestFeatures.FEATURE), is(true));
    }

}