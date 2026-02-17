class DummyClass_95708 {
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForArrayWithNull() {
        new EnumBasedFeatureProvider(ValidFeatureEnum.class, null);
    }

}