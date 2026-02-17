class DummyClass_96924 {
  @Test
  public void testSort() throws Exception {
    JobConf job = new JobConf();
    String inputPath = INPUT_DIR.getRoot().getPath();
    Path outputPath = new Path(OUTPUT_DIR.getRoot().getPath());
    outputPath.getFileSystem(job).delete(outputPath);

    WordCountUtil.writeLinesBytesFile(inputPath);

    job.setInputFormat(AvroAsTextInputFormat.class);
    job.setOutputFormat(AvroTextOutputFormat.class);
    job.setOutputKeyClass(Text.class);

    FileInputFormat.setInputPaths(job, new Path(inputPath));
    FileOutputFormat.setOutputPath(job, outputPath);

    JobClient.runJob(job);

    WordCountUtil.validateSortedFile(outputPath.toString() + "/part-00000.avro");
  }

}