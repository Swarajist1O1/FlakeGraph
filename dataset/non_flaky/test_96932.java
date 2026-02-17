class DummyClass_96932 {
  @Test
  public void testNonAvroReducer() throws Exception {
    JobConf job = new JobConf();
    Path outputPath = new Path(OUTPUT_DIR.getRoot().getPath());
    outputPath.getFileSystem(job).delete(outputPath);

    // configure input for Avro from sequence file
    AvroJob.setInputSequenceFile(job);
    AvroJob.setInputSchema(job, SCHEMA);
    FileInputFormat.setInputPaths(job, file().toURI().toString());

    // mapper is default, identity

    // use a hadoop reducer that consumes Avro input
    AvroJob.setMapOutputSchema(job, SCHEMA);
    job.setReducerClass(NonAvroReducer.class);

    // configure outputPath for non-Avro SequenceFile
    job.setOutputFormat(SequenceFileOutputFormat.class);
    FileOutputFormat.setOutputPath(job, outputPath);

    // output key/value classes are default, LongWritable/Text

    JobClient.runJob(job);

    checkFile(new SequenceFileReader<>
              (new File(outputPath.toString() + "/part-00000")));
  }

}