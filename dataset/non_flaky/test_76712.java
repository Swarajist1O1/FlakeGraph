class DummyClass_76712 {
    @Test
    public void testTargetWithoutNested() {
        final String resourceD = BASE_PKG + ".ResourceD";

        assertRegistration("FAILED", resourceD);
        assertRegistration("StaticClassOfD", resourceD + "$StaticClassOfD");
        assertRegistration("FAILED", resourceD + "$StaticClassOfD$OtherAccessibleClassOfD");
    }

}