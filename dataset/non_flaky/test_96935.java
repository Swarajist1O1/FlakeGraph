class DummyClass_96935 {
  @Test
    public void testJob() throws Exception {
    JobConf job = new JobConf();
    Path outputPath = new Path(DIR.getRoot().getPath() + "/out");
    outputPath.getFileSystem(job).delete(outputPath);

    job.setInputFormat(TextInputFormat.class);
    FileInputFormat.setInputPaths(job, DIR.getRoot().getPath() + "/in");

    job.setMapperClass(AvroTestConverter.class);
    job.setNumReduceTasks(0);

    FileOutputFormat.setOutputPath(job, outputPath);
    System.out.println(createSchema());
    AvroJob.setOutputSchema(job,
                            Pair.getPairSchema(Schema.create(Schema.Type.LONG),
                                               createSchema()));
    job.setOutputFormat(AvroOutputFormat.class);

    JobClient.runJob(job);
  }

}