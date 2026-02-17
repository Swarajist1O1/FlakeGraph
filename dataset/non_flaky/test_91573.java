class DummyClass_91573 {
    @Test
    public void testFindContainingJar() throws ClassNotFoundException {
        Assert.assertTrue(ClassUtil.findContainingJar(Class.forName("org.apache.commons.beanutils.BeanUtils")).contains("commons-beanutils"));
        Assert.assertTrue(ClassUtil.findContainingJar(Class.forName("org.apache.commons.beanutils.BeanUtils"), "core").contains("commons-beanutils-core"));
    }

}