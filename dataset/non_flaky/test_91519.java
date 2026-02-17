class DummyClass_91519 {
    @Test
    public void testGetCurrentValue()  throws Throwable  {
        KafkaInputRecordReader kafkaInputRecordReader = new KafkaInputRecordReader();
        kafkaInputRecordReader.nextKeyValue();
        assertEquals(0, kafkaInputRecordReader.getCurrentValue().getBytes().length);
    }

}