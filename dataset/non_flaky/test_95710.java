class DummyClass_95710 {
    @Test(expected = IllegalStateException.class)
    public void shouldFailForDuplicateFeatureName() {
        
        EnumBasedFeatureProvider provider = new EnumBasedFeatureProvider();
        provider.addFeatureEnum(ValidFeatureEnum.class);
        provider.addFeatureEnum(DuplicateNameFeatureEnum.class); // should throw IllegalStateException
    }

}