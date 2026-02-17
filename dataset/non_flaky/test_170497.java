class DummyClass_170497 {
    @Test
    public void testMakeName()
    {
        beanName = "mngd:bean";

        beanName = mbeanContainer.makeName(beanName);

        assertEquals("mngd_bean", beanName, "Bean name should be mngd_bean");
    }

}