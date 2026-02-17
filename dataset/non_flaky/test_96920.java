class DummyClass_96920 {
  @Test
  public void testJob() throws Exception {
    JobConf job = new JobConf();
    String dir = "target/testReflectJob";
    Path inputPath = new Path(dir + "/in");
    Path outputPath = new Path(dir + "/out");

    outputPath.getFileSystem(job).delete(outputPath);
    inputPath.getFileSystem(job).delete(inputPath);

    writeLinesFile(new File(dir+"/in"));

    job.setJobName("reflect");

    AvroJob.setInputSchema(job, ReflectData.get().getSchema(Text.class));
    AvroJob.setMapOutputSchema
      (job, new Pair(new Text(""), new Count(0L)).getSchema());
    AvroJob.setOutputSchema(job, ReflectData.get().getSchema(WordCount.class));

    AvroJob.setMapperClass(job, MapImpl.class);
    //AvroJob.setCombinerClass(job, ReduceImpl.class);
    AvroJob.setReducerClass(job, ReduceImpl.class);

    FileInputFormat.setInputPaths(job, inputPath);
    FileOutputFormat.setOutputPath(job, outputPath);

    AvroJob.setReflect(job); // use reflection

    JobClient.runJob(job);

    validateCountsFile(new File(new File(dir, "out"), "part-00000.avro"));
  }

}