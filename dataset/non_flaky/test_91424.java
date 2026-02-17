class DummyClass_91424 {
    @TestLogging("org.elasticsearch.xpack.persistent:TRACE,org.elasticsearch.cluster.service:DEBUG,org.elasticsearch.xpack.ml.action:DEBUG")
    public void testDedicatedMlNode() throws Exception {
        internalCluster().ensureAtMostNumDataNodes(0);
        // start 2 non ml node that will never get a job allocated. (but ml apis are accessible from this node)
        internalCluster().startNode(Settings.builder().put(MachineLearning.ML_ENABLED.getKey(), false));
        internalCluster().startNode(Settings.builder().put(MachineLearning.ML_ENABLED.getKey(), false));
        // start ml node
        if (randomBoolean()) {
            internalCluster().startNode(Settings.builder().put(MachineLearning.ML_ENABLED.getKey(), true));
        } else {
            // the default is based on 'xpack.ml.enabled', which is enabled in base test class.
            internalCluster().startNode();
        }
        ensureStableCluster(3);

        String jobId = "dedicated-ml-node-job";
        Job.Builder job = createJob(jobId, new ByteSizeValue(2, ByteSizeUnit.MB));
        PutJobAction.Request putJobRequest = new PutJobAction.Request(job);
        client().execute(PutJobAction.INSTANCE, putJobRequest).actionGet();

        OpenJobAction.Request openJobRequest = new OpenJobAction.Request(job.getId());
        client().execute(OpenJobAction.INSTANCE, openJobRequest).actionGet();
        assertBusy(() -> {
            ClusterState clusterState = client().admin().cluster().prepareState().get().getState();
            PersistentTasksCustomMetaData tasks = clusterState.getMetaData().custom(PersistentTasksCustomMetaData.TYPE);
            PersistentTask<?> task = tasks.getTask(MlTasks.jobTaskId(jobId));
            DiscoveryNode node = clusterState.nodes().resolveNode(task.getExecutorNode());
            assertThat(node.getAttributes(), hasEntry(MachineLearning.ML_ENABLED_NODE_ATTR, "true"));
            assertThat(node.getAttributes(), hasEntry(MachineLearning.MAX_OPEN_JOBS_NODE_ATTR, "20"));
            JobTaskState jobTaskState = (JobTaskState) task.getState();
            assertNotNull(jobTaskState);
            assertEquals(JobState.OPENED, jobTaskState.getState());
        });

        logger.info("stop the only running ml node");
        internalCluster().stopRandomNode(settings -> settings.getAsBoolean(MachineLearning.ML_ENABLED.getKey(), true));
        ensureStableCluster(2);
        assertBusy(() -> {
            // job should get and remain in a failed state and
            // the status remains to be opened as from ml we didn't had the chance to set the status to failed:
            assertJobTask(jobId, JobState.OPENED, false);
        });

        logger.info("start ml node");
        internalCluster().startNode(Settings.builder().put(MachineLearning.ML_ENABLED.getKey(), true));
        ensureStableCluster(3);
        assertBusy(() -> {
            // job should be re-opened:
            assertJobTask(jobId, JobState.OPENED, true);
        });
    }

}