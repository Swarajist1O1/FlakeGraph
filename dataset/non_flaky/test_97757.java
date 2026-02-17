class DummyClass_97757 {
    @Test
    public void testWildcards() throws NoSuchFieldException {
        TypeProcessor converter = new DefaultTypeProcessor();
        final TypeProcessor.Context context = getTestContext(converter);
        assertEquals("string[]", converter.processType(C.class.getDeclaredField("x").getGenericType(), context).getTsType().toString());
        assertEquals("any[]", converter.processType(C.class.getDeclaredField("y").getGenericType(), context).getTsType().toString());
        assertEquals("any[]", converter.processType(C.class.getDeclaredField("z").getGenericType(), context).getTsType().toString());
    }

}