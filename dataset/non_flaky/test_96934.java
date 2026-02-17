class DummyClass_96934 {
  @Test
  public void testSort() throws Exception {
    JobConf job = new JobConf();
    String inDir = "../../../share/test/data";
    Path input = new Path(inDir+"/weather.avro");
    Path output = new Path("target/test/weather-sort");

    output.getFileSystem(job).delete(output);

    job.setJobName("sort weather");

    AvroJob.setInputSchema(job, Weather.SCHEMA$);
    AvroJob.setMapOutputSchema
      (job, Pair.getPairSchema(Weather.SCHEMA$, Schema.create(Type.NULL)));
    AvroJob.setOutputSchema(job, Weather.SCHEMA$);

    AvroJob.setMapperClass(job, SortMapper.class);
    AvroJob.setReducerClass(job, SortReducer.class);

    FileInputFormat.setInputPaths(job, input);
    FileOutputFormat.setOutputPath(job, output);
    FileOutputFormat.setCompressOutput(job, true);
    AvroJob.setOutputCodec(job, SNAPPY_CODEC);

    JobClient.runJob(job);

    // check output is correct
    DatumReader<Weather> reader = new SpecificDatumReader<>();
    DataFileReader<Weather> check = new DataFileReader<>
      (new File(inDir + "/weather-sorted.avro"), reader);
    DataFileReader<Weather> sorted = new DataFileReader<>
      (new File(output.toString() + "/part-00000.avro"), reader);

    for (Weather w : sorted)
      assertEquals(check.next(), w);

    check.close();
    sorted.close();

    // check that AvroMapper and AvroReducer get close() and configure() called
    assertEquals(1, mapCloseCalls.get());
    assertEquals(1, reducerCloseCalls.get());
    assertEquals(1, mapConfigureCalls.get());
    assertEquals(1, reducerConfigureCalls.get());


  }

}