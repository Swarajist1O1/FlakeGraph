class DummyClass_95694 {
    @Test
    public void buildWillReturnNullWhenFeatureGroupAnnotationIsNotPresent() throws Exception {
        FeatureGroup result = AnnotationFeatureGroup.build(Label.class);

        assertThat(result, nullValue());
    }

}