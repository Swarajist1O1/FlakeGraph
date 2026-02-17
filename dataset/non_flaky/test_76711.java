class DummyClass_76711 {
    @Test
    public void testTargetWithNested() {
        final String resourceC = BASE_PKG + ".ResourceC";

        assertRegistration("FAILED", resourceC);
        assertRegistration("InaccessibleClassOfC", resourceC + "$InaccessibleClassOfC");
        assertRegistration("OtherInaccessibleClassOfC", resourceC + "$InaccessibleClassOfC$OtherInaccessibleClassOfC");
    }

}