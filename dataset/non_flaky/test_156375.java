class DummyClass_156375 {
    @Test
    public void testConstructor() {
        assertNotNull(new ClassPathUtils());
        final Constructor<?>[] cons = ClassPathUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(ClassPathUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(ClassPathUtils.class.getModifiers()));
    }

}