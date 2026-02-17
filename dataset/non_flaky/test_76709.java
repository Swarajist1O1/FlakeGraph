class DummyClass_76709 {
    @Test
    public void testSelfWithoutNested() {
        final String resourceA = BASE_PKG + ".ResourceA";

        assertRegistration("ResourceA", resourceA);
        assertRegistration("FAILED", resourceA + "$InnerClassOfA");
        assertRegistration("FAILED", resourceA + "$StaticClassOfA");
        assertRegistration("FAILED", resourceA + "$InterfaceOfA");
    }

}