class DummyClass_114019 {
    @Test
    public void testDecimalNumber() throws Exception {
        Object ret = reader.read("3.2");
        assertNotNull(ret);
        assertEquals(Double.class, ret.getClass());
        assertEquals(3.2, ret);
    }

}