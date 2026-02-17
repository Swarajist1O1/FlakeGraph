class DummyClass_91526 {
    @Test
    public void testLoadKafkaProperties() {
        KafkaConsumerProperties kafkaConsumerProperties = KafkaConsumerProperties.getInstanceFromEnv();
        assertFalse(kafkaConsumerProperties.extractKafkaConfigToProperties().containsKey("acks"));
        assertTrue(kafkaConsumerProperties.extractKafkaConfigToProperties().containsKey("session.timeout.ms"));
        assertEquals("10000", kafkaConsumerProperties.extractKafkaConfigToProperties().getProperty("session.timeout.ms"));
        assertTrue(kafkaConsumerProperties.extractKafkaConfigToProperties().containsKey("client.id"));
        assertEquals("kylin", kafkaConsumerProperties.extractKafkaConfigToProperties().getProperty("client.id"));
    }

}