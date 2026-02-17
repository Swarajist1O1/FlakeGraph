class DummyClass_91518 {
    @Test
    public void testGetCurrentKey()  throws Throwable  {
        KafkaInputRecordReader kafkaInputRecordReader = new KafkaInputRecordReader();
        kafkaInputRecordReader.nextKeyValue();
        assertEquals(0L, kafkaInputRecordReader.getCurrentKey().get());
    }

}