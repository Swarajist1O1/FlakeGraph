class DummyClass_170501 {
    @Test
    public void testDomain()
    {
        String domain = "Test";

        mbeanContainer.setDomain(domain);

        assertEquals(domain, mbeanContainer.getDomain(), "Domain name must be Test");
    }

}