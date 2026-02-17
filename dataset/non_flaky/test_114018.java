class DummyClass_114018 {
    @Test
    public void testExponentialNumber2() throws Exception {
        Object ret = reader.read("123.4e10");
        assertNotNull(ret);
        assertEquals(Double.class, ret.getClass());
        assertEquals(123.4e10, ret);
    }

}