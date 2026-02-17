class DummyClass_38646 {
    @Test
    public void testJsonSerialization() throws Exception {

        final String[] keyNames = { "key1", "key2" };
        final String key1Value = "test1";
        final String key2Value = "test2";
        final byte[][] keyValues = { key1Value.getBytes(), key2Value.getBytes() };
        final String param = "param";
        final String algo = "algo";
        int batchSize = 10;
        int compressionMsgSize = 10;

        // serialize to json
        byte[] data = "payload".getBytes();
        Map<String, String> properties = Maps.newHashMap();
        properties.put("prop1", "value");
        Map<String, String> metadata1 = Maps.newHashMap();
        metadata1.put("version", "v1");
        metadata1.put("ckms", "cmks-1");
        Map<String, String> metadata2 = Maps.newHashMap();
        metadata2.put("version", "v2");
        metadata2.put("ckms", "cmks-2");
        Record<byte[]> recordCtx = createRecord(data, algo, keyNames, keyValues, param.getBytes(), metadata1, metadata2,
                batchSize, compressionMsgSize, properties, true);
        String json = Utils.serializeRecordToJson(recordCtx);

        // deserialize from json and assert
        KinesisMessageResponse kinesisJsonResponse = deSerializeRecordFromJson(json);
        assertEquals(data, getDecoder().decode(kinesisJsonResponse.getPayloadBase64()));
        EncryptionCtx encryptionCtxDeser = kinesisJsonResponse.getEncryptionCtx();
        assertEquals(key1Value.getBytes(),
                getDecoder().decode(encryptionCtxDeser.getKeysMapBase64().get(keyNames[0])));
        assertEquals(key2Value.getBytes(),
                getDecoder().decode(encryptionCtxDeser.getKeysMapBase64().get(keyNames[1])));
        assertEquals(param.getBytes(), getDecoder().decode(encryptionCtxDeser.getEncParamBase64()));
        assertEquals(algo, encryptionCtxDeser.getAlgorithm());
        assertEquals(metadata1, encryptionCtxDeser.getKeysMetadataMap().get(keyNames[0]));
        assertEquals(metadata2, encryptionCtxDeser.getKeysMetadataMap().get(keyNames[1]));
        assertEquals(properties, kinesisJsonResponse.getProperties());

    }

}