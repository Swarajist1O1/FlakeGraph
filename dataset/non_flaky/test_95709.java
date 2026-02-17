class DummyClass_95709 {
    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForNonEnumType() {
        new EnumBasedFeatureProvider(NotAnEnum.class);
    }

}