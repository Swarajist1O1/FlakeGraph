class DummyClass_76710 {
    @Test
    public void testSelfWithNested() {
        final String resourceB = BASE_PKG + ".ResourceB";

        assertRegistration("ResourceB", resourceB);
        assertRegistration("InnerClassOfB", resourceB + "$InnerClassOfB");
        assertRegistration("StaticClassOfB", resourceB + "$StaticClassOfB");
        assertRegistration("InterfaceOfB", resourceB + "$InterfaceOfB");
        assertRegistration("InnerInnerOfB", resourceB + "$InnerClassOfB$InnerInnerOfB");
    }

}