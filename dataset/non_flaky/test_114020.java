class DummyClass_114020 {
    @Test
    public void testNaturalNumber() throws Exception {
        Object ret = reader.read("123");
        assertNotNull(ret);
        assertEquals(Long.class, ret.getClass());
        assertEquals(123L, ret);
    }

}