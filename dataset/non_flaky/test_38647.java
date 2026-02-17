class DummyClass_38647 {
    @Test(dataProvider="encryption")
    public void testFbSerialization(boolean isEncryption) throws Exception {

        final String[] keyNames = { "key1", "key2" };
        final String param = "param";
        final String algo = "algo";
        int batchSize = 10;
        int compressionMsgSize = 10;

        for (int k = 0; k < 5; k++) {
            String payloadString = RandomStringUtils.random(142342 * k, String.valueOf(System.currentTimeMillis()));
            final String key1Value = payloadString + "test1";
            final String key2Value = payloadString + "test2";
            final byte[][] keyValues = { key1Value.getBytes(), key2Value.getBytes() };
            byte[] data = payloadString.getBytes();
            Map<String, String> properties = Maps.newHashMap();
            properties.put("prop1", payloadString);
            Map<String, String> metadata1 = Maps.newHashMap();
            metadata1.put("version", "v1");
            metadata1.put("ckms", "cmks-1");
            Map<String, String> metadata2 = Maps.newHashMap();
            metadata2.put("version", "v2");
            metadata2.put("ckms", "cmks-2");
            Record<byte[]> record = createRecord(data, algo, keyNames, keyValues, param.getBytes(), metadata1,
                    metadata2, batchSize, compressionMsgSize, properties, isEncryption);
            ByteBuffer flatBuffer = Utils.serializeRecordToFlatBuffer(record);

            Message kinesisJsonResponse = Message.getRootAsMessage(flatBuffer);
            byte[] fbPayloadBytes = new byte[kinesisJsonResponse.payloadLength()];
            kinesisJsonResponse.payloadAsByteBuffer().get(fbPayloadBytes);
            assertEquals(data, fbPayloadBytes);

            if(isEncryption) {
                org.apache.pulsar.io.kinesis.fbs.EncryptionCtx encryptionCtxDeser = kinesisJsonResponse.encryptionCtx();
                byte compressionType = encryptionCtxDeser.compressionType();
                int fbBatchSize = encryptionCtxDeser.batchSize();
                boolean isBathcMessage = encryptionCtxDeser.isBatchMessage();
                int fbCompressionMsgSize = encryptionCtxDeser.uncompressedMessageSize();
                int totalKeys = encryptionCtxDeser.keysLength();
                Map<String, Map<String, String>> fbKeyMetadataResult = Maps.newHashMap();
                Map<String, byte[]> fbKeyValueResult = Maps.newHashMap();
                for (int i = 0; i < encryptionCtxDeser.keysLength(); i++) {
                    org.apache.pulsar.io.kinesis.fbs.EncryptionKey encryptionKey = encryptionCtxDeser.keys(i);
                    String keyName = encryptionKey.key();
                    byte[] keyValueBytes = new byte[encryptionKey.valueLength()];
                    encryptionKey.valueAsByteBuffer().get(keyValueBytes);
                    fbKeyValueResult.put(keyName, keyValueBytes);
                    Map<String, String> fbMetadata = Maps.newHashMap();
                    for (int j = 0; j < encryptionKey.metadataLength(); j++) {
                        KeyValue encMtdata = encryptionKey.metadata(j);
                        fbMetadata.put(encMtdata.key(), encMtdata.value());
                    }
                    fbKeyMetadataResult.put(keyName, fbMetadata);
                }
                byte[] paramBytes = new byte[encryptionCtxDeser.paramLength()];
                encryptionCtxDeser.paramAsByteBuffer().get(paramBytes);

                assertEquals(totalKeys, 2);
                assertEquals(batchSize, fbBatchSize);
                assertTrue(isBathcMessage);
                assertEquals(compressionMsgSize, fbCompressionMsgSize);
                assertEquals(keyValues[0], fbKeyValueResult.get(keyNames[0]));
                assertEquals(keyValues[1], fbKeyValueResult.get(keyNames[1]));
                assertEquals(metadata1, fbKeyMetadataResult.get(keyNames[0]));
                assertEquals(metadata2, fbKeyMetadataResult.get(keyNames[1]));
                assertEquals(compressionType, org.apache.pulsar.io.kinesis.fbs.CompressionType.LZ4);
                assertEquals(param.getBytes(), paramBytes);
                assertEquals(algo, encryptionCtxDeser.algo());
            }

            Map<String, String> fbproperties = Maps.newHashMap();
            for (int i = 0; i < kinesisJsonResponse.propertiesLength(); i++) {
                KeyValue property = kinesisJsonResponse.properties(i);
                fbproperties.put(property.key(), property.value());
            }
            assertEquals(properties, fbproperties);

        }
    }

}