class DummyClass_92607 {
    @Test
    public void testValueOfStringWithValueType() throws IOException {
        when(objectReader.readValue((String) any())).thenReturn(pojo);
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((String) any(), (Class<?>) any())).thenCallRealMethod();

        String source = "";
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}