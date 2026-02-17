class DummyClass_92608 {
    @Test
    public void testValueOfByteArrayWithValueType() throws IOException {
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((byte[]) any())).thenReturn(pojo);
        when(objectReader.readValue((byte[]) any(), (Class<?>) any())).thenCallRealMethod();

        byte[] source = "{}".getBytes();
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}