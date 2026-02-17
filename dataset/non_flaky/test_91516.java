class DummyClass_91516 {
    @Test
    public void testNextKeyValue()  throws Throwable  {
        KafkaInputRecordReader kafkaInputRecordReader = new KafkaInputRecordReader();
        assertFalse(kafkaInputRecordReader.nextKeyValue());
        assertFalse(kafkaInputRecordReader.nextKeyValue());
    }

}