class DummyClass_92609 {
    @Test
    public void testValueOfDataInputWithValueType() throws IOException {
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((DataInput) any())).thenReturn(pojo);
        when(objectReader.readValue((DataInput) any(), (Class<?>) any())).thenCallRealMethod();

        DataInput source = new DataInputStream(new ByteArrayInputStream("{}".getBytes()));
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}