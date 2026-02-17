class DummyClass_91527 {
    @Test
    public void testLoadKafkaPropertiesAsHadoopJobConf() throws IOException, ParserConfigurationException, SAXException {
        KafkaConsumerProperties kafkaConsumerProperties = KafkaConsumerProperties.getInstanceFromEnv();
        Configuration conf = new Configuration(false);
        conf.addResource(new FileInputStream(new File(kafkaConsumerProperties.getKafkaConsumerHadoopJobConf())), KafkaConsumerProperties.KAFKA_CONSUMER_FILE);
        assertEquals("10000", conf.get("session.timeout.ms"));

        Properties prop = KafkaConsumerProperties.extractKafkaConfigToProperties(conf);
        assertEquals("10000", prop.getProperty("session.timeout.ms"));
    }

}