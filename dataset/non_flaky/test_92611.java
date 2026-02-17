class DummyClass_92611 {
    @Test
    public void testValueOfInputStreamWithValueType() throws IOException {
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((InputStream) any())).thenReturn(pojo);
        when(objectReader.readValue((InputStream) any(), (Class<?>) any())).thenCallRealMethod();

        InputStream source = new ByteArrayInputStream("{}".getBytes());
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}