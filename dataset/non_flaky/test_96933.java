class DummyClass_96933 {
  @Test
  public void testMapOnly() throws Exception {
    JobConf job = new JobConf();
    String inDir = System.getProperty("share.dir","../../../share")+"/test/data";
    Path input = new Path(inDir+"/weather.avro");
    Path output = new Path("target/test/weather-ident");

    output.getFileSystem(job).delete(output);

    job.setJobName("identity map weather");

    AvroJob.setInputSchema(job, Weather.SCHEMA$);
    AvroJob.setOutputSchema(job, Weather.SCHEMA$);

    FileInputFormat.setInputPaths(job, input);
    FileOutputFormat.setOutputPath(job, output);
    FileOutputFormat.setCompressOutput(job, true);

    job.setNumReduceTasks(0);                     // map-only

    JobClient.runJob(job);

    // check output is correct
    DatumReader<Weather> reader = new SpecificDatumReader<>();
    DataFileReader<Weather> check = new DataFileReader<>
      (new File(inDir + "/weather.avro"), reader);
    DataFileReader<Weather> sorted = new DataFileReader<>
      (new File(output.toString() + "/part-00000.avro"), reader);

    for (Weather w : sorted)
      assertEquals(check.next(), w);

    check.close();
    sorted.close();
  }

}