class DummyClass_92612 {
    @Test
    public void testValueOfJsonNodeWithValueType() throws IOException {
        when(objectReader.forType((Class<?>) any())).thenReturn(objectReader);
        when(objectReader.readValue((JsonNode) any())).thenReturn(pojo);
        when(objectReader.readValue((JsonNode) any(), (Class<?>) any())).thenCallRealMethod();

        JsonNode source = new TextNode("{}");
        POJO result = objectReader.readValue(source, POJO.class);

        assertEquals(result, pojo);
        verify(objectReader).forType(POJO.class);
        verify(objectReader).readValue(source);
    }

}