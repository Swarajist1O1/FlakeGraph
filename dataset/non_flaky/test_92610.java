class DummyClass_92610 {
    @Test
    public void testValueOfFileWithValueType() throws IOException {
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((File) any())).thenReturn(pojo);
        when(objectReader.readValue((File) any(), (Class<?>) any())).thenCallRealMethod();

        File source = new File("unknownpath");
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}