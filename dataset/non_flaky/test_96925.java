class DummyClass_96925 {
  @Test
  public void testJob() throws Exception {
    JobConf job = new JobConf();
    Path inputPath1 = new Path(INPUT_DIR_1.getRoot().getPath());
    Path inputPath2 = new Path(INPUT_DIR_2.getRoot().getPath());
    Path outputPath = new Path(OUTPUT_DIR.getRoot().getPath());

    outputPath.getFileSystem(job).delete(outputPath);

    writeNamesFiles(new File(inputPath1.toUri().getPath()));
    writeBalancesFiles(new File(inputPath2.toUri().getPath()));

    job.setJobName("multiple-inputs-join");
    AvroMultipleInputs.addInputPath(job, inputPath1, NamesMapImpl.class,
            ReflectData.get().getSchema(NamesRecord.class));
    AvroMultipleInputs.addInputPath(job, inputPath2, BalancesMapImpl.class,
            ReflectData.get().getSchema(BalancesRecord.class));

    Schema keySchema = ReflectData.get().getSchema(KeyRecord.class);
    Schema valueSchema = ReflectData.get().getSchema(JoinableRecord.class);
    AvroJob.setMapOutputSchema(job,
            Pair.getPairSchema(keySchema, valueSchema));
    AvroJob.setOutputSchema(job,
            ReflectData.get().getSchema(CompleteRecord.class));

    AvroJob.setReducerClass(job, ReduceImpl.class);
    job.setNumReduceTasks(1);

    FileOutputFormat.setOutputPath(job, outputPath);

    AvroJob.setReflect(job);

    JobClient.runJob(job);

    validateCompleteFile(new File(OUTPUT_DIR.getRoot(), "part-00000.avro"));
  }

}