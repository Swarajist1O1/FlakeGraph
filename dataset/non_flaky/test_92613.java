class DummyClass_92613 {
    @Test
    public void testValueOfReaderWithValueType() throws IOException {
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((Reader) any())).thenReturn(pojo);
        when(objectReader.readValue((Reader) any(), (Class<?>) any())).thenCallRealMethod();

        Reader source = new StringReader("{}");
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}