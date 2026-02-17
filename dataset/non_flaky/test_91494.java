class DummyClass_91494 {
    @Test
    public void testVersion() {
        Driver driver = new DummyDriver();
        DriverVersion version = driver.getDriverVersion();
        Assert.assertNotEquals("unknown version", version.productVersion);
    }

}