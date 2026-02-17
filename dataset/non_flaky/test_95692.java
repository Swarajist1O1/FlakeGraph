class DummyClass_95692 {
    @Test(expected=UnsupportedOperationException.class)
    public void setState() {
        
        repository.setFeatureState(new FeatureState(TestFeature.F1, true));
    }

}