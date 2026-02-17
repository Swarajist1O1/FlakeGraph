class DummyClass_57259 {
  @Test
  public void testAdminOnlyEndpoints() {
    // Get all classes with @Path annotation anywhere in recon.
    Reflections reflections = new Reflections(
        "org.apache.hadoop.ozone.recon",
        new TypeAnnotationsScanner(),
        new SubTypesScanner());
    Set<Class<?>> allEndpoints =
        reflections.getTypesAnnotatedWith(Path.class);

    Assert.assertFalse(allEndpoints.isEmpty());

    // If an endpoint is added, it must be explicitly added to this set or be
    // marked with @AdminOnly for this test to pass.
    Set<Class<?>> nonAdminEndpoints = new HashSet<>();
    nonAdminEndpoints.add(UtilizationEndpoint.class);
    nonAdminEndpoints.add(ClusterStateEndpoint.class);
    nonAdminEndpoints.add(MetricsProxyEndpoint.class);
    nonAdminEndpoints.add(NodeEndpoint.class);
    nonAdminEndpoints.add(PipelineEndpoint.class);
    nonAdminEndpoints.add(TaskStatusService.class);

    Assert.assertTrue(allEndpoints.containsAll(nonAdminEndpoints));

    Set<Class<?>> adminEndpoints = Sets.difference(allEndpoints,
        nonAdminEndpoints);

    for (Class<?> endpoint: nonAdminEndpoints) {
      Assert.assertFalse(String.format("Endpoint class %s has been " +
              "declared as non admin in this test, but is marked as " +
              "@AdminOnly.", endpoint),
          endpoint.isAnnotationPresent(AdminOnly.class));
    }

    for (Class<?> endpoint: adminEndpoints) {
      Assert.assertTrue(String.format("Endpoint class %s must be marked as " +
              "@AdminOnly or explicitly declared as non admin in this test.",
          endpoint),
          endpoint.isAnnotationPresent(AdminOnly.class));
    }
  }

}