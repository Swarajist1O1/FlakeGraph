class DummyClass_96930 {
  @Test
  public void testNonAvroMapper() throws Exception {
    JobConf job = new JobConf();
    Path outputPath = new Path(OUTPUT_DIR.getRoot().getPath());
    outputPath.getFileSystem(job).delete(outputPath);

    // configure input for non-Avro sequence file
    job.setInputFormat(SequenceFileInputFormat.class);
    FileInputFormat.setInputPaths(job, file().toURI().toString());

    // use a hadoop mapper that emits Avro output
    job.setMapperClass(NonAvroMapper.class);

    // reducer is default, identity

    // configure output for avro
    FileOutputFormat.setOutputPath(job, outputPath);
    AvroJob.setOutputSchema(job, SCHEMA);

    JobClient.runJob(job);

    checkFile(new DataFileReader<>
              (new File(outputPath.toString() + "/part-00000.avro"),
               new SpecificDatumReader<>()));
  }

}