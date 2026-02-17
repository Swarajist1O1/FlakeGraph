class DummyClass_91517 {
    @Test
    public void testGetProgress()  throws Throwable  {
        KafkaInputRecordReader kafkaInputRecordReader = new KafkaInputRecordReader();
        assertEquals(1.0F, kafkaInputRecordReader.getProgress(), 0.01F);
    }

}