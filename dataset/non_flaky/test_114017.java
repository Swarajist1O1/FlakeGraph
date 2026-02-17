class DummyClass_114017 {
    @Test
    public void testExponentialNumber() throws Exception {
        Object ret = reader.read("5e-5");
        assertNotNull(ret);
        assertEquals(Double.class, ret.getClass());
        assertEquals(5.0E-5, ret);
    }

}