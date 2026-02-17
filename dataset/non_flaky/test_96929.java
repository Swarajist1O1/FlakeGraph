class DummyClass_96929 {
  @Test
  public void testSequenceFileInputFormat() throws Exception {
    JobConf job = new JobConf();
    Path outputPath = new Path(OUTPUT_DIR.getRoot().getPath());
    outputPath.getFileSystem(job).delete(outputPath);

    // configure input for Avro from sequence file
    AvroJob.setInputSequenceFile(job);
    FileInputFormat.setInputPaths(job, file().toURI().toString());
    AvroJob.setInputSchema(job, SCHEMA);

    // mapper is default, identity
    // reducer is default, identity

    // configure output for avro
    AvroJob.setOutputSchema(job, SCHEMA);
    FileOutputFormat.setOutputPath(job, outputPath);

    JobClient.runJob(job);

    checkFile(new DataFileReader<>
              (new File(outputPath.toString() + "/part-00000.avro"),
               new SpecificDatumReader<>()));
  }

}